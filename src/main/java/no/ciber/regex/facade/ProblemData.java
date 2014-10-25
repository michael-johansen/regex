package no.ciber.regex.facade;

import no.ciber.regex.persistence.model.Match;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Component
public class ProblemData {
    private String self;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    @Valid
    private List<MatchData> matchList;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMatchList(List<MatchData> matchList) {
        this.matchList = matchList;
    }

    public List<MatchData> getMatchList() {
        return matchList;
    }
}
