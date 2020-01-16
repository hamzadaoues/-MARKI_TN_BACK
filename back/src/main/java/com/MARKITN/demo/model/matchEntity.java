package com.MARKITN.demo.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "matchEntity")
public class matchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String fixture_id;
    private String equipe_A ;
    private String equipe_B ;
    private boolean live;
    private boolean finished;
    // it can be A or B
    private String winner;
    private int equipe_A_score ;
    private int equipe_B_score ;
    private String time;
    private String last_update;

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipe_A() {
        return equipe_A;
    }

    public void setEquipe_A(String equipe_A) {
        this.equipe_A = equipe_A;
    }

    public String getEquipe_B() {
        return equipe_B;
    }

    public void setEquipe_B(String equipe_B) {
        this.equipe_B = equipe_B;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getEquipe_A_score() {
        return equipe_A_score;
    }

    public void setEquipe_A_score(int equipe_A_score) {
        this.equipe_A_score = equipe_A_score;
    }

    public int getEquipe_B_score() {
        return equipe_B_score;
    }

    public void setEquipe_B_score(int equipe_B_score) {
        this.equipe_B_score = equipe_B_score;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public String getFixture_id() {
        return fixture_id;
    }
    public void setFixture_id(String fixture_id) {
        this.fixture_id = fixture_id;
    }

}
