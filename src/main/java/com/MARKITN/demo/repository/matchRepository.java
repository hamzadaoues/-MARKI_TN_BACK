package com.MARKITN.demo.repository;


import com.MARKITN.demo.model.betMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface matchRepository extends JpaRepository<betMatch, Long> {
}
