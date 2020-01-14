package com.MARKITN.demo.service;

import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.model.matchEntity;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class update_database {

    @Autowired(required = true)
    com.MARKITN.demo.service.matchEntityService matchEntityService;
    @Autowired
    com.MARKITN.demo.service.BetMatchService betMatchService;

    private final RestTemplate restTemplate;
    private final String urlAPI = "https://livescore-api.com/api-client/scores/live.json?key=x4UzBMvqIjAh04MV&secret=sNNslkq3ZhsoeJCMNxmokRP2bpkbt76W";

    public update_database(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(fixedRate = 5000)
    public void update_match() {
        String res = " ";
        try {
            res = this.restTemplate.getForObject(urlAPI, String.class);
            JSONObject jsonObject = new JSONObject(res);
            JSONObject newJSON = jsonObject.getJSONObject("data");
            jsonObject = new JSONObject(newJSON.toString());
            JSONArray listMatch = jsonObject.getJSONArray("match");
            for (int i = 0; i < listMatch.length(); i++) {
                JSONObject match = (JSONObject) listMatch.get(i);
                String fixture_id = match.getString("fixture_id");
                String liveScore = match.getString("score");
                String equipa_A = match.getString("home_name");
                String equipa_B = match.getString("away_name");
                String[] scores = liveScore.split(" - ");
                int score_A = Integer.parseInt(scores[0]);
                int score_B = Integer.parseInt(scores[1]);
                // find our matchEntity
                matchEntity matchEntity = this.matchEntityService.findMatchByFixtureId(fixture_id);
                //System.out.println(matchEntity.getEquipe_A());
                if (matchEntity != null) {
                    matchEntity.setEquipe_A_score(score_A);
                    matchEntity.setEquipe_B_score(score_B);
                    matchEntity.setEquipe_A(equipa_A);
                    matchEntity.setEquipe_B(equipa_B);
                    if (score_A > score_B) matchEntity.setWinner("A");
                    if (score_A < score_B) matchEntity.setWinner("B");
                    if (score_A == score_B) matchEntity.setWinner("draw");
                    matchEntity.setTime(match.getString("time"));
                    // last update date
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    matchEntity.setLast_update(formatter.format(date));
                    // update live variable
                    if (match.getString("status").equals("IN PLAY") || match.getString("status").equals("ADDED TIME"))
                        matchEntity.setLive(true);
                    else
                        matchEntity.setLive(false);
                    if (match.getString("status").equals("FINISHED"))
                        matchEntity.setFinished(true);
                    // saving update
                    this.matchEntityService.save(matchEntity);
                }
            }
            System.out.println("Table EntityMatch is up to date \n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Scheduled(fixedRate = 9000)
    // update betMatch if the match is finished
    public void update_betMatch() {
        List<betMatch> betMatchs = this.betMatchService.getAllBetMatch();
        for (int i = 0; i < betMatchs.size(); i++) {
            betMatch betMatch = betMatchs.get(i);
            betMatch.setFinished(betMatch.getMatch().isFinished());
            this.betMatchService.updateBetMatch(betMatch);
        }
        System.out.println("Table BetMatch is up to date \n");
    }
}
