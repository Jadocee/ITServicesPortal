package com.spacejaam.itservicesportal.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleDAO articleDAO;

    @Autowired
    ArticleController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping(value = "/knowledge_base")
    public ModelAndView displayArticles() {
        ModelAndView modelAndView = new ModelAndView("knowledge-base");
        final List<Article> articles = this.articleDAO.getKnowledgeBaseArticles();
        modelAndView.addObject("foundArticles", !articles.isEmpty());
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @GetMapping(value = "/knowledge_base/{id}")
    public ModelAndView displayArticle(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("article");
        final Article article = this.articleDAO.getArticleById(id);
        modelAndView.addObject("article", article);
        return modelAndView;
    }

}
