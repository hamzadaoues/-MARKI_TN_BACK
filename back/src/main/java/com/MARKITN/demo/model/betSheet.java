package com.MARKITN.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "betsheet")
public class betSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "betSheet_id")
    private List<betMatch> betMatches;
    private boolean validated = true;
    private boolean finished = false;
    private boolean wonSheet;
    // state can be : eliminated ,won , in progress
    // initialement : in progress
    private String state = "in progress";
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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
