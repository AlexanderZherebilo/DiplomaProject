package com.springproject.ZherebiloAV.repos;

import com.springproject.ZherebiloAV.domain.ProgressTopic;
import com.springproject.ZherebiloAV.domain.Topic;
import com.springproject.ZherebiloAV.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressTopicRepo extends CrudRepository<ProgressTopic, Long> {
    List<ProgressTopic> findAll();

    ProgressTopic findByTopicAndUser(Topic topic, User user);
}
