package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.*;
import com.springproject.ZherebiloAV.repos.ProgressTopicRepo;
import com.springproject.ZherebiloAV.service.LanguageService;
import com.springproject.ZherebiloAV.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @Autowired
    ProgressTopicRepo progressTopicRepo;

    @Autowired
    TopicService topicService;

    @Value("${upload.path}")
    private String uploadPath;

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

    @GetMapping("courses/topics/{topic}")
    public String openTopic(
            Model model,
            @AuthenticationPrincipal User user,
            @PathVariable Topic topic)
    {
        ProgressTopic pr = progressTopicRepo.findByTopicAndUser(topic, user);
        model.addAttribute("progress", pr);
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("courses/addLanguage")
    public String addCourse(Model model) {
        model.addAttribute("operation", "ADD");
        return "addLanguage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("courses/addLanguage")
    public String saveCourse(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        Language lang = new Language(name, description);
        setFilePath(lang, image);
        languageService.saveCourse(lang);
        return "redirect:/courses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/courses/{language}/addTopic")
    public String addTopic(Model model, @PathVariable Language language) {
        model.addAttribute("lang", language);
        model.addAttribute("operation", "ADD");
        return "addTopic";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("courses/{language}/addTopic")
    public String saveTopic(
            @PathVariable Language language,
            @RequestParam String name,
            @RequestParam String theory
    ) {
        Topic topic = new Topic(name, theory);
        topic.setLanguage(language);
        languageService.saveTopic(topic);
        return "redirect:/courses/{language}";
    }

    @PostMapping("courses/topics/{topic}")
    public String checkTest(
            @AuthenticationPrincipal User user,
            @PathVariable Topic topic,
            @RequestParam List<String> answers
    ) {
        if (languageService.checkTopic(topic, answers))
            languageService.addPoints(user, topic);
        else languageService.addMistake(user, topic);
        return "redirect:{topic}";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("courses/topics/{topic}/editTopic")
    public String editTopic(
            @PathVariable Topic topic,
            Model model
    ) {
        model.addAttribute("lang", topic.getLanguage());
        model.addAttribute("operation", "EDIT");
        return "addTopic";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("courses/topics/{topic}/editTopic")
    public String updateTopic(
            @PathVariable Topic topic,
            @RequestParam String name,
            @RequestParam String theory
    ) {
        topic.setName(name);
        topic.setTheory(theory);
        topicService.updateTopic(topic);
        return "redirect:/courses/topics/{topic}";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("courses/{language}/editCourse")
    public String editCourse(
            @PathVariable Language language,
            Model model
    ) {
        model.addAttribute("operation", "EDIT");
        return "addLanguage";
    }

    @PreAuthorize(("hasAuthority('ADMIN')"))
    @PostMapping("courses/{language}/editCourse")
    public String updateCourse(
            @PathVariable Language language,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image
    ) throws IOException {
        language.setName(name);
        language.setDescription(description);
        setFilePath(language, image);
        languageService.saveCourse(language);
        return "redirect:/courses/{language}";
    }

    private void setFilePath(@PathVariable Language language, @RequestParam(required = false) MultipartFile image) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "," + image.getOriginalFilename();

            image.transferTo(new File(uploadPath + "/" + resultFilename));

            language.setImage(resultFilename);
        }
    }
}
