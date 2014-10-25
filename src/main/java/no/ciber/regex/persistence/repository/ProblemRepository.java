package no.ciber.regex.persistence.repository;

import no.ciber.regex.persistence.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This software is written by Michael on 21/10/2014.
 */
public interface ProblemRepository extends JpaRepository<Problem, String> {
    @Override
    @Query("SELECT p FROM Problem p JOIN FETCH p.matchList WHERE p.id = :id")
    Problem getOne(@Param("id") String s);
}
