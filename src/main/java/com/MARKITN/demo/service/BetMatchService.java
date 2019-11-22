package com.MARKITN.demo.service;

import com.MARKITN.demo.model.betMatch;
import org.springframework.stereotype.Service;

@Service
public interface BetMatchService {

    boolean isCorrect(betMatch betMatch);
}
