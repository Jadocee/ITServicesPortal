package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.bean.client.Client;
import com.spacejaam.itservicesportal.bean.client.Role;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public ModelAndView display(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView("__Layout");
        modelAndView.addObject("pageTitle", "Login");
        modelAndView.addObject("pageName", "login");
        Client client = new Client();
        client.init("Jaydon", "Cameron", "j@e", "33", Role.USER);
        session.setAttribute("client", client);
        return modelAndView;
    }

}
