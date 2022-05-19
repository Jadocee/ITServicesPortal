package com.spacejaam.itservicesportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping(value = "/login?error")
    public ModelAndView getLoginViewWithError() {
        final ModelAndView modelAndView = new ModelAndView("login");
        // TODO handle login error
        return modelAndView;
    }

    /*@PostMapping(value = "/register/submit")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerAccount(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName, @RequestParam("email") String email,
                                  @RequestParam("contactNum") String contactNum) {

    }*/


}
