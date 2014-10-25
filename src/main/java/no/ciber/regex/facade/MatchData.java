package no.ciber.regex.facade;

import no.ciber.regex.persistence.model.MatchType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * This software is written by Michael on 23/10/2014.
 */
public class MatchData {
    @NotNull
    private MatchType matchType;
    @NotNull
    @Length(min = 0, max = 256)
    private String test;

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
