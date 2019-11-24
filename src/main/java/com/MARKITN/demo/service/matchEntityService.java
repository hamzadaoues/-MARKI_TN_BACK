package com.MARKITN.demo.service;

import com.MARKITN.demo.model.matchEntity;

import java.util.List;

public interface matchEntityService {
    matchEntity addMatch(matchEntity matchEntity);

    List<matchEntity> findAllMatches();

    matchEntity findMatchById(Long matchId);

    matchEntity findMatchByFixtureId(String FixtureId);

    matchEntity save(matchEntity match);
}
