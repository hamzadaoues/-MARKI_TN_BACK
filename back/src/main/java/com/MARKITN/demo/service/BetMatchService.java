package com.MARKITN.demo.service;

import com.MARKITN.demo.model.betMatch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BetMatchService {

    boolean isCorrect(betMatch betMatch);

    betMatch addBetMatch(betMatch betMatch);

    betMatch updateBetMatch(betMatch betMatch);

    List<betMatch> getAllBetMatch();

}
