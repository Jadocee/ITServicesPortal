package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.model.client.ClientPrinciple;
import com.spacejaam.itservicesportal.model.client.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping(value = "/")
    public ModelAndView index(HttpSession session) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof ClientPrinciple) {
            final ClientPrinciple clientPrinciple = (ClientPrinciple) authentication.getPrincipal();
            session.setAttribute("username", clientPrinciple.getUsername());
            session.setAttribute("isUSER", clientPrinciple.hasRole(Role.USER));
            session.setAttribute("isITSTAFF", clientPrinciple.hasRole(Role.ITSTAFF));
            session.setAttribute("isLoggedIn", true);
        } else {
            session.setAttribute("isLoggedIn", false);
        }
        System.out.println(authentication.getPrincipal());

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
