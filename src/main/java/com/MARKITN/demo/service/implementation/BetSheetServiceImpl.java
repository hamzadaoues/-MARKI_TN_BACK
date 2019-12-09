package com.MARKITN.demo.service.implementation;

import com.MARKITN.demo.model.betSheet;
import com.MARKITN.demo.service.BetSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetSheetServiceImpl implements BetSheetService {
    @Autowired
    com.MARKITN.demo.repository.betSheetRepository betSheetRepository;


    @Override
    public betSheet addBetSheet(betSheet betSheet) {
        return this.betSheetRepository.save(betSheet);
    }

    @Override
    public List<betSheet> getAllBetSheet() {
        return this.betSheetRepository.findAll();
    }

    @Override
    public betSheet updateBetSheet(betSheet betSheet) {
        return this.betSheetRepository.save(betSheet);
    }
}
