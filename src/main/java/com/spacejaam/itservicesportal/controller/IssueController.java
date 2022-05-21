package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.dao.issue.IssueDAO;
import com.spacejaam.itservicesportal.model.issue.Category;
import com.spacejaam.itservicesportal.model.issue.Issue;
import com.spacejaam.itservicesportal.model.issue.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueDAO issueDAO;

    @Autowired
    IssueController(IssueDAO issueDAO) {
        this.issueDAO = issueDAO;
    }

    @GetMapping("/new")
    public ModelAndView displayForm() {
        ModelAndView modelAndView = new ModelAndView("createissue");
        return modelAndView;
    }

    @PostMapping("/new")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String createIssue(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subCategory,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        this.issueDAO.insertIssue(
                new Issue(
                        title,
                        description,
                        Category.valueOf(category),
                        SubCategory.valueOf(subCategory)
                ),
                (String) request.getSession().getAttribute("username")
//                ((ClientPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        );
        return "index";
    }
}
