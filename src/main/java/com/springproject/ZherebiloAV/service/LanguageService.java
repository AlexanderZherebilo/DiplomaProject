package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.Language;
import com.springproject.ZherebiloAV.domain.ProgressTopic;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.repos.LanguageRepo;
import com.springproject.ZherebiloAV.repos.ProgressTopicRepo;
import com.springproject.ZherebiloAV.repos.TopicRepo;
import com.springproject.ZherebiloAV.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LanguageService {
    @Autowired
    LanguageRepo languageRepo;

    @Autowired
    ProgressTopicRepo progressTopicRepo;

    @Autowired
    UserRepo userRepo;

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

    public boolean checkTopic(Topic topic, List<String> answers) {
        boolean res = true;
        for (int i=0; i<answers.size(); i++) {
            if (!topic.getQuiz().get(i).getAnswer().equals(answers.get(i)))
                res = false;
        }
        return res;
    }

    public void addPoints(User user, Topic topic) {
        ProgressTopic progress = progressTopicRepo.findByTopicAndUser(topic, user);
        if (progress == null) {
            progress = new ProgressTopic(topic, user);
            progressTopicRepo.save(progress);
        }
        if (progress.getErrors() == 0)
            user.setPoints(user.getPoints()+100);
        if (progress.getErrors() == 1)
            user.setPoints(user.getPoints()+50);
        if (progress.getErrors() >= 2)
            user.setPoints(user.getPoints()+25);
        user.setTopics(user.getTopics()+1);
        progress.setCompleted(true);

        progressTopicRepo.save(progress);
        userRepo.save(user);
    }

    public void addMistake(User user, Topic topic) {
        ProgressTopic progress = progressTopicRepo.findByTopicAndUser(topic, user);
        if (progress == null) {
            progress = new ProgressTopic(topic, user);
            progressTopicRepo.save(progress);
        }
        progress.setErrors(progress.getErrors()+1);
        progressTopicRepo.save(progress);
    }

    public Set<Language> getAllUsersLanguages(User user) {
        List<Language> all = findAll();
        List<ProgressTopic> progresses = user.getProgresses();
        Set<Language> nw = new HashSet<>();
        for (int i=0; i<all.size(); i++)
            for (int j=0; j<progresses.size(); j++)
                if (progresses.get(j).getTopic().getLanguage().getName().equals(all.get(i).getName())) {
                    nw.add(all.get(i));
                }
        return nw;
    }

    public List<Language> getNotEmptyCourses() {
        List<Language> list = new ArrayList<>();
        Iterable<Language> it = languageRepo.findAll();
        for (Language e : it)
            if (e.getTopics().size() != 0)
                list.add(e);
        return list;
    }

    public List<Language> getEmptyCourses() {
        List<Language> list = new ArrayList<>();
        Iterable<Language> it = languageRepo.findAll();
        for (Language e : it)
            if (e.getTopics().size() == 0)
                list.add(e);
        return list;
    }
}
