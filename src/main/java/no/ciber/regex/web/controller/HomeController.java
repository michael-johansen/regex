package no.ciber.regex.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * This software is written by Michael on 23/10/2014.
 */
@Controller
@RequestMapping(produces = "text/html")
public class HomeController {
    @RequestMapping("/")
    public ModelAndView modelAndView(){
        return new ModelAndView(new InternalResourceView("/static/view/index.html"));
    }
}
