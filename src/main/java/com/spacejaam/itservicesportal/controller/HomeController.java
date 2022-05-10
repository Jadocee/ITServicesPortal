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
public class HomeController {

  public static ModelAndView getLayout(final String title, final String page) {
    ModelAndView modelAndView = new ModelAndView("__Layout");
    modelAndView.addObject("title", title);
    modelAndView.addObject("content", page);
    return modelAndView;
  }

  @GetMapping(value = "/")
  public ModelAndView home() {
    return getLayout("Home", "index");
  }
}
