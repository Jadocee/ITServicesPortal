package com.spacejaam.itservicesportal.controller;

import javax.servlet.http.HttpServletRequest;
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

  @GetMapping(value = "/")
  public ModelAndView home(HttpServletRequest request) {
//    if ((Client) )

    ModelAndView modelAndView = new ModelAndView("__Layout");
    modelAndView.addObject("title", "Home");
    modelAndView.addObject("content", "index");
    return modelAndView;
  }
}
