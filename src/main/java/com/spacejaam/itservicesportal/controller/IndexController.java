package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.bean.client.ClientPrinciple;
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
        if (session.getAttribute("userData") == null) {
            final Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principle instanceof ClientPrinciple) {
                session.setAttribute("userData", ((ClientPrinciple) principle).getUsername());
            }
        }
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
