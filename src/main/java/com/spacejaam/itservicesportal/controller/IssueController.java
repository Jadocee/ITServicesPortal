package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.model.issue.Issue;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    @GetMapping("/issues/new")
    public ModelAndView displayForm() {
        ModelAndView modelAndView = new ModelAndView("createissue");
        return modelAndView;
    }

    @PostMapping("/issues/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createIssue(@RequestBody Issue issue) {

    }
}
