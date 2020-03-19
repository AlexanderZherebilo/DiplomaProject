package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.service.LanguageService;
import com.springproject.ZherebiloAV.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    LanguageService languageService;

    @Autowired
    TopicService topicService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "main";
    }

    @GetMapping("/myProfile")
    public String myProfile(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("userLangs", languageService.getAllUsersLanguages(user));
        model.addAttribute("startedLangs", languageService.getAllUsersLanguages(user).size());
        model.addAttribute("langNum", languageService.findAll().size());
        model.addAttribute("topicNum", topicService.getTestNum().size());
        model.addAttribute("allTopics", topicService.findAll().size());
        model.addAttribute("topicsWithout", topicService.getWithoutQuiz());
        model.addAttribute("notEmptyLangs", languageService.getNotEmptyCourses().size());
        model.addAttribute("langsWithout", languageService.getEmptyCourses());
        return "profile";
    }
}
