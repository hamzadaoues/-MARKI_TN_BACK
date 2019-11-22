package com.MARKITN.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "betMatch")
public class betMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "match_id")
    private matchEntity match;
    private int equipe_A_score;
    private int equipe_B_score;
    // it can be A or B
    private String winner;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public matchEntity getMatch() {
        return match;
    }

    public void setMatch(matchEntity match) {
        this.match = match;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}