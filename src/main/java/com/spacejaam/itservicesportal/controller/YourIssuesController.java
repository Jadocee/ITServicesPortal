package com.spacejaam.itservicesportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class YourIssuesController {

    @GetMapping(value = "/yourissues")
    public ModelAndView yourIssues() {
        final ModelAndView modelAndView = new ModelAndView("yourissues");
//        modelAndView.addObject("pageTitle", "Your Issues");
//        modelAndView.addObject("pageName", "yourissues");
        return modelAndView;
    }
}
