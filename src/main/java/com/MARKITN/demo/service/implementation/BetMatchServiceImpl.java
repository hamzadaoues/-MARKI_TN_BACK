package com.MARKITN.demo.service.implementation;


import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.service.BetMatchService;
import org.springframework.stereotype.Service;

@Service
public class BetMatchServiceImpl implements BetMatchService {



    // used only if the match is finished
    @Override
    public boolean isCorrect(betMatch betMatch) {
        return betMatch.getWinner().equals(betMatch.getMatch().getWinner());
    }
}
