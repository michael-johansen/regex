package no.ciber.regex.persistence.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This software is written by Michael on 23/10/2014.
 */
@Entity
public class Match extends AbstractPersistable<String> {
    @NotNull
    @ManyToOne
    private Problem problem;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MatchType matchType;
    @NotNull
    @Column(length = 512)
    private String test;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
