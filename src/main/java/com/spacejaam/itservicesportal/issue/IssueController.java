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
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
        modelAndView.addObject("comments", this.commentDAO.getCommentsByIssueId(id));
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
        this.commentDAO.insertComment(
                id,
                (String) session.getAttribute("username"),
                new Comment(message)
        );
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
        );
        // TODO: redirect to the created issue
        return "index";
    }

    @GetMapping("/tracker")
    public ModelAndView displayTracker(@RequestParam(value = "state", required = false) String state) {
        final ModelAndView modelAndView = new ModelAndView("issues-tracker");
        modelAndView.addObject(
                "issues",
                state != null ? this.issueDAO.getIssuesByState(State.valueOf(state)) : this.issueDAO.getAllIssues()
        );
        return modelAndView;
    }


    @GetMapping("/tracker/manage_issue/{id}")
    public ModelAndView manageIssue(@PathVariable("id") Long id) {
        final ModelAndView modelAndView = new ModelAndView("issue-manager");
        modelAndView.addObject("issue", this.issueDAO.getIssueById(id));
        modelAndView.addObject("comments", this.commentDAO.getCommentsByIssueId(id));
//        modelAndView.addObject("recommended", this.commentDAO.getRecommendedCommentForIssue(id));
        return modelAndView;
    }

    @PostMapping(
            value = "/tracker/recommend_comment",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public void recommendAsSolution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        if (bufferedReader.ready()) {
            JsonReader jsonReader = new JsonReader(bufferedReader);
            JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
            final Long id = jsonObject.get("id").getAsLong();
            this.commentDAO.markCommentAsSolution(id);
        }
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
    ) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        if (bufferedReader.ready()) {
            JsonReader jsonReader = new JsonReader(bufferedReader);
            JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
            final String tag = jsonObject.get("tag").getAsString();
            this.issueDAO.updateIssueTag(id, Tag.valueOf(tag));
        }
    }


}
