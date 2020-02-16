package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.Language;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.repos.LanguageRepo;
import com.springproject.ZherebiloAV.repos.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {
    @Autowired
    LanguageRepo languageRepo;

    @Autowired
    TopicRepo topicRepo;

    public List<Language> findAll() {
        List<Language> list = new ArrayList<>();
        Iterable<Language> it = languageRepo.findAll();
        for (Language e : it)
            list.add(e);
        return list;
    }

    public void saveCourse(Language language) {
        languageRepo.save(language);
    }

    public void saveTopic(Topic topic) {
        languageRepo.save(topic.getLanguage());
        topicRepo.save(topic);
    }
}
