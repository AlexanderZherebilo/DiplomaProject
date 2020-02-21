package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.Question;
import com.springproject.ZherebiloAV.repos.QuestionRepo;
import com.springproject.ZherebiloAV.repos.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    TopicRepo topicRepo;

    @Autowired
    QuestionRepo questionRepo;

    public void saveQuestion(Question question) {
        topicRepo.save(question.getTopic());
        questionRepo.save(question);
    }
}
