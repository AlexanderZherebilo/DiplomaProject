package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.Language;
import com.springproject.ZherebiloAV.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "languageList";
    }

    @GetMapping("/courses/{language}")
    public String openCourse(Model model, @PathVariable Language language) {
        model.addAttribute("language", language);
        return "topicsList";
    }
}
