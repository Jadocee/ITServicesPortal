package com.spacejaam.itservicesportal.controller;

import org.springframework.stereotype.Controller;
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
  public ModelAndView index() {
//    ModelAndView modelAndView = new ModelAndView("__Layout");
      ModelAndView modelAndView = new ModelAndView("index");
      modelAndView.addObject("pageTitle", "Home");
//    modelAndView.addObject("pageName", "index");
      return modelAndView;
  }
}
