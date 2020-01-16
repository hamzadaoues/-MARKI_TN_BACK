package com.MARKITN.demo.service.implementation;

import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.service.updateSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.MARKITN.demo.model.betSheet;

import java.util.List;

@Service
public class updateSheetImpl implements updateSheet {

    @Autowired
    com.MARKITN.demo.service.BetSheetService betSheetService;
    @Autowired
    com.MARKITN.demo.service.BetMatchService betMatchService;


    @Scheduled(fixedRate = 6000)
    @Override
    public void update() {
        List<betSheet> betSheets = this.betSheetService.getAllBetSheet();
        for (int i = 0; i < betSheets.size(); i++) {
            betSheet betSheet = betSheets.get(i);
            boolean eliminated = false;
            Integer nbFinishedMatch = 0;
            if (!betSheet.isFinished()) {
                List<betMatch> betMatchs = betSheet.getBetMatches();
                for (int j = 0; j < betMatchs.size(); j++) {
                    boolean matchFinished = betMatchs.get(j).isFinished();
                    if (matchFinished) {
                        nbFinishedMatch++;
                        boolean isCorrect = this.betMatchService.isCorrect(betMatchs.get(j));
                        if (!isCorrect) {
                            eliminated = true;
                        }
                    }
                }

                // one BetMatch is wrong played
                if (eliminated) {
                    betSheet.setFinished(true);
                    betSheet.setState("eliminated");
                    this.betSheetService.updateBetSheet(betSheet);
                    continue;
                }

                // All match are finished correctry
                if (nbFinishedMatch == betSheet.getBetMatches().size()) {
                    betSheet.setFinished(true);
                    betSheet.setState("won");
                    this.betSheetService.updateBetSheet(betSheet);
                }
            }


        }
    }
}
