package no.ciber.regex.facade;

import no.ciber.regex.persistence.model.Match;
import no.ciber.regex.persistence.model.Problem;
import no.ciber.regex.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Component
public class ProblemFacade {
    @Autowired
    private ProblemService problemService;

    @Cacheable(value = "problemData", key = "#pageable")
    public Page<ProblemData> findAll(Pageable pageable) {
        List<ProblemData> problemDataList = new ArrayList<ProblemData>();
        Page<Problem> problemPage = problemService.findAll(pageable);
        for (Problem problem : problemPage) {
            ProblemData convert = convert(problem);
            problemDataList.add(convert);
        }
        return new PageImpl<ProblemData>(problemDataList, pageable, problemPage.getTotalElements());
    }

    // TODO: Extract to converter
    public ProblemData convert(Problem problem) {
        ProblemData problemData = new ProblemData();
        problemData.setSelf("/problem/" + problem.getId());
        problemData.setName(problem.getName());
        problemData.setDescription(problem.getDescription());
        problemData.setMatchList(new ArrayList<MatchData>());
        for (Match match : problem.getMatchList()) {
            problemData.getMatchList().add(convert(match));
        }
        return problemData;
    }

    public MatchData convert(Match match) {
        MatchData matchData = new MatchData();
        matchData.setMatchType(match.getMatchType());
        matchData.setTest(match.getTest());
        return matchData;
    }

    @Cacheable(value = "problemData", key = "#id")
    public ProblemData getOne(String id) {
        return convert(problemService.getOne(id));
    }

    @CacheEvict(value = "problemData", key = "#result")
    public String save(ProblemData problemData) {
        Problem problem = new Problem();
        problem.setName(problemData.getName());
        problem.setDescription(problemData.getDescription());
        problem.setMatchList(new ArrayList<Match>());
        for (MatchData matchData : problemData.getMatchList()) {
            Match match = new Match();
            match.setProblem(problem);
            match.setMatchType(matchData.getMatchType());
            match.setTest(matchData.getTest());
            problem.getMatchList().add(match);
        }
        return problemService.save(problem).getId();
    }
}
