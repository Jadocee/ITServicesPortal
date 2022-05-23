package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.model.client.ClientPrinciple;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

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
            session.setAttribute("userId", clientPrinciple.getId());
            final ArrayList<String> authList = new ArrayList<>();
            for (final GrantedAuthority authority : clientPrinciple.getAuthorities()) {
                authList.add(authority.getAuthority());
            }
            session.setAttribute("authorities", authList);
//            session.setAttribute("isUSER", clientPrinciple);
//            session.setAttribute("isITSTAFF", clientPrinciple.hasRole(Role.ITSTAFF));
            session.setAttribute("isLoggedIn", true);
        } else {
            session.setAttribute("isLoggedIn", false);
        }
        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("resolvedStats", performanceDAO.getResolvedCount());
//        modelAndView.addObject("unresolvedStats", performanceDAO.getUnresolvedCount());
//        modelAndView.addObject("stressRate", performanceDAO.getStressRate());
        return modelAndView;
    }


}
