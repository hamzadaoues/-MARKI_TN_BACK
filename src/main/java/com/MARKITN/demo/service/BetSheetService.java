package com.MARKITN.demo.service;

import org.springframework.stereotype.Service;
import com.MARKITN.demo.model.betSheet;

import java.util.List;

@Service
public interface BetSheetService {
    betSheet addBetSheet(betSheet betSheet);

    List<betSheet> getAllBetSheet();

    betSheet updateBetSheet(betSheet betSheet);
}
