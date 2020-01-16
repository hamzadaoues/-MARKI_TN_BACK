package com.MARKITN.demo.service.implementation;

import com.MARKITN.demo.model.matchEntity;
import com.MARKITN.demo.repository.matchRepository;
import com.MARKITN.demo.service.matchEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class matchEntityServiceImpl implements matchEntityService {
    @Autowired
    matchRepository matchRepository;

    @Override
    public matchEntity addMatch(matchEntity matchEntity) {

        return (this.matchRepository.save(matchEntity));
    }

    @Override
    public List<matchEntity> findAllMatches() {
        return null;
    }

    @Override
    public matchEntity findMatchById(Long matchId) {
        return null;
    }

    @Override
    public matchEntity findMatchByFixtureId(String FixtureId) {
        return this.matchRepository.findmatchEntityByFixture_Id(FixtureId);
    }

    @Override
    public matchEntity save(matchEntity match) {
        return this.matchRepository.save(match);
    }
}
