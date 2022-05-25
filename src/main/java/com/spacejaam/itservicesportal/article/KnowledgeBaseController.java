package com.spacejaam.itservicesportal.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KnowledgeBaseController {

    @GetMapping(value = "/knowledge_base")
    public ModelAndView knowledgebase(){
        ModelAndView modelAndView = new ModelAndView("knowledge-base");
        return modelAndView;
    }

}
