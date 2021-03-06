package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.Role;
import com.springproject.ZherebiloAV.domain.User;
import com.springproject.ZherebiloAV.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPoints(0);
        user.setTopics(0);

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    public boolean updateUser(String name, String surname, String username, String password, String email, Long id) {
        List<User> allUsers = findAll();
        User nUser = new User();

        for (User u : allUsers) {
            if (u.getId() == id)
                nUser = u;

            if (u.getUsername().equals(username) && u.getId() != id) {
                return false;
            }
        }

        nUser.setEmail(email);
        nUser.setActive(false);
        nUser.setActivationCode(UUID.randomUUID().toString());

        nUser.setName(name);
        nUser.setSurname(surname);
        nUser.setUsername(username);
        nUser.setPassword(passwordEncoder.encode(password));

        userRepo.save(nUser);

        updatingMessage(nUser);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Приветствуем, %s! \n" +
                            "Добро пожаловать в приложение Solo Learn. Чтобы завершить регистрацию аккаунта, перейдите по ссылке: " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации аккаунта", message);
        }
    }

    private void updatingMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Приветствуем, %s! \n" +
                            "В ваши данные были внесены изменения. Требуется повторная активация аккаунта. Перейдите по ссылке: " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Повторная активация аккаунта", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> it = userRepo.findAll();
        for (User e : it)
            list.add(e);
        return list;
    }
}
