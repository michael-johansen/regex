package no.ciber.regex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.ciber.regex.persistence.PersistenceConfiguration;
import no.ciber.regex.persistence.model.Match;
import no.ciber.regex.persistence.model.Problem;
import no.ciber.regex.persistence.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Configuration
@ComponentScan
@Import(PersistenceConfiguration.class)
public class ServiceConfig {
    @Autowired
    private Environment env;

    @Autowired
    public void initData(ProblemRepository problemRepository) throws IOException {
        if(env.getProperty("persistence.deleteAll", Boolean.class, false)){
            problemRepository.deleteAll();
        }
        if(problemRepository.count() == 0){
            ClassPathResource resource = new ClassPathResource("data.json");
            Problem[] problems = new ObjectMapper().readValue(resource.getInputStream(), Problem[].class);
            for (Problem problem : problems) {
                for (Match match : problem.getMatchList()) {
                    match.setProblem(problem);
                }
            }
            problemRepository.save(Arrays.asList(problems));
        }
    }
}
