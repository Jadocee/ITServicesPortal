package com.spacejaam.itservicesportal.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

  @GetMapping(value = "/")
  public ModelAndView index(Model model, HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("__Layout");
    modelAndView.addObject("pageTitle", "Home");
    modelAndView.addObject("pageName", "index");
    return modelAndView;
  }
}
