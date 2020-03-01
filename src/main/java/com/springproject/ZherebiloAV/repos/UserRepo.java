package com.springproject.ZherebiloAV.repos;

import com.springproject.ZherebiloAV.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByActivationCode(String code);

    List<User> findAll();
}