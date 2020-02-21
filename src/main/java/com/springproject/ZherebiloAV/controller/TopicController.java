package com.springproject.ZherebiloAV.controller;

import com.springproject.ZherebiloAV.domain.Option;
import com.springproject.ZherebiloAV.domain.Question;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.service.LanguageService;
import com.springproject.ZherebiloAV.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class TopicController {
    @Autowired
    TopicService topicService;

    @Value("${upload.path}")
    private String uploadPath;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("topics/{topic}/setQuiz")
    public String addQuiz(
        @PathVariable Topic topic,
        Model model
    ) {
        model.addAttribute("topic", topic);
        return "addQuiz";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("topics/{topic}/setQuiz")
    public String saveQuiz(
        @PathVariable Topic topic,
        @RequestParam String problem,
        @RequestParam List<String> options,
        @RequestParam String answer
    ) {
        List<Option> optionList = new ArrayList<>();
        if (options.get(0) != "")
        for (int i=0; i<options.size(); i++)
            optionList.add(new Option(options.get(i)));
        Question question = new Question(problem, optionList, answer);
        for (Option opt : question.getOptions())
            opt.setQuestion(question);
        question.setTopic(topic);
        topicService.saveQuestion(question);
        return "redirect:/courses/topics/{topic}";
    }
}
