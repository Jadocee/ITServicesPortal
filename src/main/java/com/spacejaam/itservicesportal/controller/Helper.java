package com.spacejaam.itservicesportal.controller;

import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
public class Helper {

  public static ModelAndView getLayout(String pageTitle, String pageName) {
    ModelAndView modelAndView = new ModelAndView("__Layout");
    modelAndView.addObject("pageTitle", pageTitle);
    modelAndView.addObject("pageName", pageName);
    return modelAndView;
  }

}
