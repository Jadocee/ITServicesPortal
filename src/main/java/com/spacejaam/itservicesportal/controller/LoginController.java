package com.spacejaam.itservicesportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/a")
    public ModelAndView display() {
        ModelAndView modelAndView = new ModelAndView("__Layout");
        modelAndView.addObject("content", "index");
        modelAndView.addObject("title", "Home");
        modelAndView.addObject("client", "");

        return modelAndView;
    }

}
