package com.MARKITN.demo.repository;


import com.MARKITN.demo.model.betMatch;
import com.MARKITN.demo.model.matchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface matchRepository extends JpaRepository<matchEntity, Long> {

    @Query("SELECT m FROM matchEntity m where m.fixture_id = ?1")
    public matchEntity findmatchEntityByFixture_Id(String fixture_id);

}
