package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.bean.issue.Issue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
public class ReportController {

    @GetMapping("/report")
    public ModelAndView displayForm(Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("issues");
        modelAndView.addObject("pageTitle", "Create Issue");
        modelAndView.addObject("pageName", "issueform");
        return modelAndView;
    }

    @PostMapping("/report/submit")
    public String createIssue(@ModelAttribute("issue") Issue issue, BindingResult result,
                              ModelMap model) {
        if (result.hasErrors()) {
            return "issueform";
        }
        model.addAttribute("issueTitle", Issue.getTitle());
        model.addAttribute("description", Issue.getDescription());
        model.addAttribute("categories", Issue.getCategory());
        return "index";
    }
}
