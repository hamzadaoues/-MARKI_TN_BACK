package com.MARKITN.demo.repository;


import com.MARKITN.demo.model.User;
import com.MARKITN.demo.model.betSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface betSheetRepository extends JpaRepository<betSheet, Long> {
    List<betSheet> findAllByUser(User user);
}
