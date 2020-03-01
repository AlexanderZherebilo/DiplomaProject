package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/settings")
    public String openSettings(Model model) {

        return "settings";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userList")
    public String getUser(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "userList";
    }

    @GetMapping("/raiting")
    public String getRaiting(Model model) {

        return "raitings";
    }
}
