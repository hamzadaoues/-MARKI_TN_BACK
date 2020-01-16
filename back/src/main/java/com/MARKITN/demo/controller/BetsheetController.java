package com.MARKITN.demo.controller;


import com.MARKITN.demo.model.User;
import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.model.betSheet;
import com.MARKITN.demo.model.matchEntity;
import com.MARKITN.demo.security.model.UserPrincipal;
import com.MARKITN.demo.service.BetSheetService;
import com.MARKITN.demo.service.implementation.BetMatchServiceImpl;
import com.MARKITN.demo.service.implementation.UserServiceImpl;
import com.MARKITN.demo.service.implementation.matchEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    UserServiceImpl userService;


    // add new betSheet ( creation )
    @PostMapping
    public ResponseEntity<betSheet> addBetSheet(@RequestBody betSheet betSheet) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long user_id = ((UserPrincipal) principal).getId();
        User user = this.userService.findUserById(user_id);
        System.out.println(betSheet.getState());
        List<betMatch> betMatches = betSheet.getBetMatches();
        for (int i = 0; i < betMatches.size(); i++) {
            betMatch betmatch = betSheet.getBetMatches().get(i);
            matchEntity match = betmatch.getMatch();
            match = this.matchEntityService.addMatch(match);
            betmatch = this.betMatchService.addBetMatch(betmatch);
        }
       user.addBetSheet(betSheet);
       // user = this.userService.updateUser(user);
        betSheet.setUser(user);
        betSheet = this.betSheetService.addBetSheet(betSheet);

        return new ResponseEntity<>(betSheet, HttpStatus.CREATED);

    }

    // Retrieve all betSheets
    @GetMapping
    public ResponseEntity<List<betSheet>> findAllBetSheets() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        Long user_id = ((UserPrincipal) principal).getId();
        User user = this.userService.findUserById(user_id);
        List<betSheet> betSheets = betSheetService.findAllBetSheetsByUser(user);
        if (betSheets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(betSheets, HttpStatus.OK);
    }
}
