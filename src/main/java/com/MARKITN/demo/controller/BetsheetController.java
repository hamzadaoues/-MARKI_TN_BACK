package com.MARKITN.demo.controller;


import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.model.betSheet;
import com.MARKITN.demo.model.matchEntity;
import com.MARKITN.demo.service.BetSheetService;
import com.MARKITN.demo.service.implementation.BetMatchServiceImpl;
import com.MARKITN.demo.service.implementation.matchEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/betsheet")
public class BetsheetController {
    @Autowired
    BetSheetService betSheetService;
    @Autowired
    BetMatchServiceImpl betMatchService;
    @Autowired
    matchEntityServiceImpl matchEntityService;


    // add new betSheet ( creation )
    @PostMapping
    public ResponseEntity<betSheet> addBetSheet(@RequestBody betSheet betSheet) throws IOException {
        System.out.println(betSheet.getState());
        List<betMatch> betMatches = betSheet.getBetMatches();
        for (int i = 0; i < betMatches.size(); i++) {
            betMatch betmatch = betSheet.getBetMatches().get(i);
            matchEntity match = betmatch.getMatch();
            match = this.matchEntityService.addMatch(match);
            betmatch = this.betMatchService.addBetMatch(betmatch);
        }
        betSheet = this.betSheetService.addBetSheet(betSheet);
        return new ResponseEntity<>(betSheet, HttpStatus.CREATED);

    }

    // Retrieve all betSheets
    @GetMapping
    public ResponseEntity<List<betSheet>> findAllBetSheets() {
        List<betSheet> betSheets = betSheetService.getAllBetSheet();
        if (betSheets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(betSheets, HttpStatus.OK);
    }
}
