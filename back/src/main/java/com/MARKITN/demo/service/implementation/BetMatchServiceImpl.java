package com.MARKITN.demo.service.implementation;


import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.service.BetMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetMatchServiceImpl implements BetMatchService {
    @Autowired
    com.MARKITN.demo.repository.betMatchRepository betMatchRepository;


    // used only if the match is finished
    @Override
    public boolean isCorrect(betMatch betMatch) {
        if (betMatch.getMatch().isFinished())
            return betMatch.getWinner().equals(betMatch.getMatch().getWinner());
        else return true;
    }

    @Override
    public betMatch addBetMatch(betMatch betMatch) {
        return this.betMatchRepository.save(betMatch);
    }

    @Override
    public betMatch updateBetMatch(betMatch betMatch) {
        return this.betMatchRepository.save(betMatch);
    }

    @Override
    public List<betMatch> getAllBetMatch() {
        return this.betMatchRepository.findAll();
    }
}
