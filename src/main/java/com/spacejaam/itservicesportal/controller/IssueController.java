package com.spacejaam.itservicesportal.controller;

import com.spacejaam.itservicesportal.dao.issue.IssueDAO;
import com.spacejaam.itservicesportal.model.client.ClientPrinciple;
import com.spacejaam.itservicesportal.model.issue.Category;
import com.spacejaam.itservicesportal.model.issue.Comment;
import com.spacejaam.itservicesportal.model.issue.Issue;
import com.spacejaam.itservicesportal.model.issue.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/created_by_me")
    public ModelAndView displayYourIssues() {
        final ModelAndView modelAndView = new ModelAndView("yourissues");
        final Long clientId = ((ClientPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        final List<Issue> yourIssues = this.issueDAO.getIssuesByAuthorId(clientId);
        modelAndView.addObject("issues", yourIssues);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView displayIssue(@PathVariable("id") Long id) {
        final ModelAndView modelAndView = new ModelAndView("issue");
        modelAndView.addObject("issue", this.issueDAO.getIssueById(id));
        modelAndView.addObject("comments", this.issueDAO.getCommentsByIssueId(id));
        return modelAndView;
    }

    @PostMapping("/{id}/new_comment")
    @ResponseStatus(HttpStatus.CREATED)
    public RedirectView createComment(
            @PathVariable("id") Long id,
            @RequestParam("commentBody") String message,
            HttpSession session,
            HttpServletRequest request
    ) {
        this.issueDAO.insertComment(
                id,
                (String) session.getAttribute("username"),
                new Comment(message)
        );
//        if (request.isUserInRole("ROLE_ITSTAFF")) {
//            this.issueDAO.updateIssueState(id, S)
//        }
        return new RedirectView(request.getContextPath() + "/issues/" + id);
    }


    @PostMapping("/new")
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
        // TODO: redirect to the created issue
        return "index";
    }
}
