package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.bean.client.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@Controller
public class LoginController {

//  @GetMapping(value = "/login")
//  public ModelAndView display(HttpSession session) {
//    final ModelAndView modelAndView = new ModelAndView("__Layout");
//    modelAndView.addObject("pageTitle", "Login");
//    modelAndView.addObject("pageName", "login");
//    Client client = new Client();
//    client.init("Jaydon", "Cameron", "j@e", "33", Role.USER);
//    session.setAttribute("client", client);
//    return modelAndView;
//  }

  @PostMapping(value = "/register/submit")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String registerAccount(@RequestParam("firstName") String firstName,
      @RequestParam("lastName") String lastName, @RequestParam("email") String email,
      @RequestParam("contactNum") String contactNum) {
    final Client newClient = new Client(firstName, lastName, email, contactNum);
    return "/login";
  }


}
