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
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/")
    public ModelAndView display(HttpServletRequest request) {
        ModelAndView modelAndView = Helper.getLayout("Login", "login");

        return modelAndView;
    }

}
