package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("Error", "Выбранный логин занят!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("color", "success");
            model.addAttribute("message", "Пользователь успешно активирован");
        }
        else {
            model.addAttribute("color", "danger");
            model.addAttribute("message", "Код активации не найден, либо недействителен");
        }

        return "activate";
    }
}