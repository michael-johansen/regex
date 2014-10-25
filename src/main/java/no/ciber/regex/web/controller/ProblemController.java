package no.ciber.regex.web.controller;

import no.ciber.regex.facade.ProblemData;
import no.ciber.regex.facade.ProblemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This software is written by Michael on 21/10/2014.
 */
@RestController
@RequestMapping(value = "/api/problem", produces = "application/json")
public class ProblemController {
    @Autowired
    private ProblemFacade problemFacade;

    @RequestMapping(method = RequestMethod.GET)
    public Page<ProblemData> findAll(@PageableDefault Pageable pageable) {
        return problemFacade.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProblemData getOne(@PathVariable("id") String id) {
        return problemFacade.getOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@Valid @RequestBody ProblemData problemData) {
        problemFacade.save(problemData);
    }
}
