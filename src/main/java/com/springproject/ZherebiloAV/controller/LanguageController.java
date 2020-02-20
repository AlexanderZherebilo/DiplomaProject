package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.Language;
import com.springproject.ZherebiloAV.domain.Question;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String openTopic(Model model, @PathVariable Topic topic) {
        model.addAttribute("topic", topic);
        return "topic";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("courses/addLanguage")
    public String addCourse(Model model) {

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
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "," + image.getOriginalFilename();

            image.transferTo(new File(uploadPath + "/" + resultFilename));

            lang.setImage(resultFilename);
        }
        languageService.saveCourse(lang);
        return "redirect:/courses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/courses/{language}/addTopic")
    public String addTopic(Model model, @PathVariable Language language) {
        model.addAttribute("lang", language);
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
}
