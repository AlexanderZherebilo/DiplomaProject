package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.ProgressTopic;
import com.springproject.ZherebiloAV.domain.Question;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.repos.LanguageRepo;
import com.springproject.ZherebiloAV.repos.ProgressTopicRepo;
import com.springproject.ZherebiloAV.repos.QuestionRepo;
import com.springproject.ZherebiloAV.repos.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    LanguageRepo languageRepo;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    ProgressTopicRepo progressTopicRepo;

    public void saveQuestion(Question question) {
        topicRepo.save(question.getTopic());
        questionRepo.save(question);
    }

    public List<Topic> getTestNum() {
        List<Topic> list = new ArrayList<>();
        Iterable<Topic> it = topicRepo.findAll();
        for (Topic e : it)
            if (e.hasQuestions())
            list.add(e);
        return list;
    }

    public List<Topic> getWithoutQuiz() {
        List<Topic> list = new ArrayList<>();
        Iterable<Topic> it = topicRepo.findAll();
        for (Topic e : it)
            if (!e.hasQuestions())
                list.add(e);
        return list;
    }

    public List<Topic> findAll() {
        List<Topic> list = new ArrayList<>();
        Iterable<Topic> it = topicRepo.findAll();
        for (Topic e : it)
            list.add(e);
        return list;
    }

    public void updateTopic(Topic topic) {
        languageRepo.save(topic.getLanguage());
        topicRepo.save(topic);
    }

    public void deleteQuestion(Question question) {
        questionRepo.delete(question);
    }
}
