package com.MARKITN.demo.controller;

import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.model.betSheet;
import com.MARKITN.demo.model.matchEntity;
import com.MARKITN.demo.service.BetSheetService;
import com.MARKITN.demo.service.implementation.matchEntityServiceImpl;
import com.MARKITN.demo.service.implementation.BetMatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {
    @Autowired
    matchEntityServiceImpl matchEntityService;
    @Autowired
    BetMatchServiceImpl betMatchService;
    @Autowired
    com.MARKITN.demo.service.update_database update_database;
    @Autowired
    BetSheetService betSheetService;
    @Autowired
    com.MARKITN.demo.service.implementation.updateSheetImpl updateSheet;

    @GetMapping
    public void testing() {
        System.out.println("here");
        // update_database.update_match();
        //    System.out.println(s);
/*
        matchEntity m = new matchEntity();
        m.setEquipe_A("Aarau");
        m.setEquipe_B("Grasshopper");
        m.setEquipe_A_score(1);
        m.setEquipe_B_score(0);
        m.setFixture_id("1317557");
        m.setLive(false);
        m.setFinished(true);
        m.setWinner("A");
        matchEntity m_added = this.matchEntityService.addMatch(m);
        betMatch betMatch = new betMatch();
        betMatch.setEquipe_A_score(2);
        betMatch.setEquipe_B_score(0);
        betMatch.setWinner("A");
        betMatch.setFinished(true);
        betMatch.setMatch(m_added);
        this.betMatchService.addBetMatch(betMatch);
        betSheet betSheet = new betSheet();

        //
        matchEntity m2 = new matchEntity();
        m2.setEquipe_A("Aarau");
        m2.setEquipe_B("Grasshopper");
        m2.setEquipe_A_score(1);
        m2.setEquipe_B_score(0);
        m2.setFixture_id("1317557");
        m2.setLive(false);
        m2.setFinished(true);
        m2.setWinner("B");
        matchEntity m_added2 = this.matchEntityService.addMatch(m);
        m_added2 = this.matchEntityService.addMatch(m2);
        betMatch betMatch2 = new betMatch();
        betMatch2.setEquipe_A_score(2);
        betMatch2.setEquipe_B_score(0);
        betMatch2.setWinner("A");
        betMatch2.setFinished(false);
        betMatch2.setMatch(m_added2);
        this.betMatchService.addBetMatch(betMatch2);

        betSheet.setBetMatches(this.betMatchService.getAllBetMatch());
        this.betSheetService.addBetSheet(betSheet);
        this.update_database.update_betMatch();


        this.updateSheet.update();*/
        matchEntity m = new matchEntity();
        m.setFixture_id("1319461");
        this.matchEntityService.addMatch(m);
    }
}
