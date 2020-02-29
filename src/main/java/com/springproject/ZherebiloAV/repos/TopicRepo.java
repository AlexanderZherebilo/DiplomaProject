package com.springproject.ZherebiloAV.repos;

import com.springproject.ZherebiloAV.domain.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepo extends CrudRepository<Topic, Long> {
    List<Topic> findAll();
}
