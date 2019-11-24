package com.MARKITN.demo.service;

import com.MARKITN.demo.model.matchEntity;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class update_database {

    @Autowired(required = true)
    com.MARKITN.demo.service.matchEntityService matchEntityService;

    private final RestTemplate restTemplate;
    private final String urlAPI = "https://livescore-api.com/api-client/scores/live.json?key=bdWSx0InpkiiTDK0&secret=8beDrImgcxkN3qJfEF7bGQz6aSo3w94E";

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
                String[] scores = liveScore.split(" - ");
                int score_A = Integer.parseInt(scores[0]);
                int score_B = Integer.parseInt(scores[1]);
                // find our matchEntity
                matchEntity matchEntity = this.matchEntityService.findMatchByFixtureId(fixture_id);
                 //  System.out.println(matchEntity.getEquipe_A());
                if (matchEntity != null) {
                    matchEntity.setEquipe_A_score(score_A);
                    matchEntity.setEquipe_B_score(score_B);
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
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Table match is up to date \n");
    }
}
