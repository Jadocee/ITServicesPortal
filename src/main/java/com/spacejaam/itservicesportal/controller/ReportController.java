package com.spacejaam.itservicesportal.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class ReportController {

  @GetMapping("/report")
  public ModelAndView displayForm(Model model, HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("__Layout");
    modelAndView.addObject("pageTitle", "Create Issue");
    modelAndView.addObject("pageName", "issueform");
    return modelAndView;
  }

//  @PostMapping("/report/submit")
//  public String createIssue(Model model) {
//
//  }
}
