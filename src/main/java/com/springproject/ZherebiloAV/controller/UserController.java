package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.repos.UserRepo;
import com.springproject.ZherebiloAV.service.LanguageService;
import com.springproject.ZherebiloAV.service.TopicService;
import com.springproject.ZherebiloAV.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    LanguageService languageService;

    @Autowired
    TopicService topicService;

    @GetMapping("/settings")
    public String openSettings() {
        return "settings";
    }

    @PostMapping("/settings")
    public String updateSettings(
            Model model,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam Long id
    ) {
        if (!userService.updateUser(name, surname, username, password, email, id)) {
            model.addAttribute("Error", "Нельзя использовать занятый логин другого аккаунта");
            return "settings";
        }

        return "redirect:/login";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList")
    public String getUser(@RequestParam(required = false, defaultValue = "") String nickname, Model model) {
        User user = userRepo.findByUsername(nickname);
        if (nickname != null && !nickname.isEmpty() && user != null) {
            List<User> users = new ArrayList<>();
            users.add(user);
            model.addAttribute("userList", users);
        } else {
            Iterable<User> users = userRepo.findAll();
            model.addAttribute("userList", users);
        }
        return "userList";
    }

    @GetMapping("/raiting")
    public String getRaiting(Model model) {

        return "raitings";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userProfile/{user}")
    public String userProfile (Model model, @PathVariable User user) {
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
