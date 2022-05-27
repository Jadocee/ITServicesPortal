package com.spacejaam.itservicesportal.issue;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.spacejaam.itservicesportal.client.ClientPrinciple;
import com.spacejaam.itservicesportal.comment.Comment;
import com.spacejaam.itservicesportal.comment.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueDAO issueDAO;
    private final CommentDAO commentDAO;

    @Autowired
    IssueController(IssueDAO issueDAO, CommentDAO commentDAO) {
        this.issueDAO = issueDAO;
        this.commentDAO = commentDAO;
    }

    @GetMapping("/new")
    public ModelAndView displayForm() {
        ModelAndView modelAndView = new ModelAndView("create-issue");
        return modelAndView;
    }

    @GetMapping("/created_by_me")
    public ModelAndView displayYourIssues() {
        final ModelAndView modelAndView = new ModelAndView("your-issues");
        final Long clientId = ((ClientPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        final List<Issue> yourIssues = this.issueDAO.getIssuesByAuthorId(clientId);
        modelAndView.addObject("foundIssues", !yourIssues.isEmpty());
        modelAndView.addObject("issues", yourIssues);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView displayIssue(@PathVariable("id") Long id, HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView("issue");
        final Issue issue = this.issueDAO.getIssueById(id);
        final List<Comment> comments = this.commentDAO.getCommentsByIssueId(id);
        modelAndView.addObject("showComments", !comments.isEmpty());
        modelAndView.addObject("allowCommenting", !issue.state().equals(State.RESOLVED));
        modelAndView.addObject("isAuthor", Objects.equals(issue.getAuthor().getId(), session.getAttribute("userId")));
        modelAndView.addObject("issue", issue);
        modelAndView.addObject("comments", this.commentDAO.getCommentsByIssueId(id));
        return modelAndView;
    }

    @PostMapping("/{id}/new_comment")
    public String createComment(
            @PathVariable("id") Long id,
            @RequestParam("commentBody") String message,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        this.commentDAO.insertComment(
                id,
                (String) session.getAttribute("username"),
                new Comment(message)
        );
        return "redirect:/issues/" + id;
    }


    @PostMapping("/new")
    public void createIssue(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subCategory,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) throws IOException {
        this.issueDAO.insertIssue(
                new Issue(
                        title,
                        description,
                        Category.valueOf(category),
                        SubCategory.valueOf(subCategory)
                ),
                (String) request.getSession().getAttribute("username")
        );
        // TODO redirect to the created issue
        response.sendRedirect(request.getContextPath() + "/issues/created_by_me");
    }

    @GetMapping("/tracker")
    public ModelAndView displayTracker() {
        final ModelAndView modelAndView = new ModelAndView("issues-tracker");
        final Map<String, List<Issue>> issues = this.issueDAO.getAllIssues();
        modelAndView.addObject("foundIssues", !issues.isEmpty());
        modelAndView.addObject("issues", issues);
        return modelAndView;
    }


    @GetMapping("/tracker/manage_issue/{id}")
    public ModelAndView manageIssue(@PathVariable("id") Long id) {
        final ModelAndView modelAndView = new ModelAndView("issue-manager");
        final Issue issue = this.issueDAO.getIssueById(id);
        modelAndView.addObject("allowCommenting", !issue.state().equals(State.RESOLVED));
        modelAndView.addObject("issue", issue);
        final List<Comment> comments = this.commentDAO.getCommentsByIssueId(id);
        modelAndView.addObject("hasCommments", !comments.isEmpty());
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @PostMapping(
            value = "/tracker/recommend_comment",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void recommendAsSolution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonObject = getJSON(request.getReader());
        final Long id = jsonObject.get("id").getAsLong();
        this.commentDAO.markCommentAsSolution(id);
    }

    @PostMapping(
            value = "/tracker/manage_issue/{id}/add_tag",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void addTag(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            JsonObject jsonObject = getJSON(request.getReader());
            final String tag = jsonObject.get("tag").getAsString();
            this.issueDAO.updateIssueTag(id, Tag.valueOf(tag));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(
            value = "/tracker/manage_issue/{id}/remove_tag",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void removeTag(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            JsonObject jsonObject = getJSON(request.getReader());
            final String tag = jsonObject.get("tag").getAsString();
            this.issueDAO.removeTag(id, Tag.valueOf(tag));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(
            value = "{id}/comments/{commentId}/review",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void reviewSolution(
            @PathVariable("id") Long id,
            @PathVariable("commentId") Long commentId,
            HttpServletRequest request
    ) {
        try {
            JsonObject jsonObject = getJSON(request.getReader());
            if (jsonObject.get("accept").getAsBoolean()) {
                this.issueDAO.acceptSolution(id, commentId);
            } else {
                this.issueDAO.rejectSolution(id, commentId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private JsonObject getJSON(BufferedReader bufferedReader) throws IOException {
        return JsonParser.parseReader(new JsonReader(bufferedReader)).getAsJsonObject();
    }
}
