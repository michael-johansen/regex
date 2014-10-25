package no.ciber.regex.service;

import no.ciber.regex.persistence.model.Match;
import no.ciber.regex.persistence.model.MatchType;
import no.ciber.regex.persistence.model.Problem;
import no.ciber.regex.persistence.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Service
@Transactional
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public Page<Problem> findAll(Pageable pageable) {
        return problemRepository.findAll(pageable);
    }

    public void verifyExpression(String expressionToTest, String problemToTest){
        Problem one = problemRepository.getOne(problemToTest);
        if(one != null){
            for (Match match : one.getMatchList()) {
                String test = match.getTest();
                boolean matches = test.matches(expressionToTest);
                if(!matches && MatchType.POSITIVE == match.getMatchType()){
                    throw new IllegalArgumentException("Did not match valid test value: [" + test + "]");
                }
                if(matches && MatchType.NEGATIVE == match.getMatchType()){
                    throw new IllegalArgumentException("Matched invalid test value: [" + test + "]");
                }
            }
        }
    }

    public Problem getOne(String id) {
        return problemRepository.getOne(id);
    }

    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }
}
