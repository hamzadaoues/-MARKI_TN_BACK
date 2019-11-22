package com.MARKITN.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "betsheet")
public class betSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "betMatch_id")
    private List<betMatch> betMatches;
    private boolean validated = true;
    private boolean finished = false;
    private boolean wonSheet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<betMatch> getBetMatches() {
        return betMatches;
    }

    public void setBetMatches(List<betMatch> betMatches) {
        this.betMatches = betMatches;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isWonSheet() {
        return wonSheet;
    }

    public void setWonSheet(boolean wonSheet) {
        this.wonSheet = wonSheet;
    }
}
