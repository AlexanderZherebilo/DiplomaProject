package com.springproject.ZherebiloAV.domain;

import com.springproject.ZherebiloAV.repos.ProgressTopicRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Логин не может быть пустым")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
    private boolean active;
    @Email
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    private String name;
    private String surname;

    private String activationCode;

    private Integer points;
    private Integer topics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProgressTopic> progresses;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.topics = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public double getLangProgress(Language language) {
        List<Topic> langTopics = language.getTopics();
        List<Topic> complTopics = new ArrayList<>();
        for (int i=0; i<this.progresses.size(); i++) {
            if (this.progresses.get(i).getTopic().getLanguage().getId().equals(language.getId()) && this.progresses.get(i).isCompleted())
                complTopics.add(this.progresses.get(i).getTopic());
        }
        double result = (double)complTopics.size() / (double) langTopics.size() * 100;
        return result;
    }

    public int getTotalFinishedCourses(List<Language> langs) {
        int result = 0;
        for (Language lang : langs)
            if (getLangProgress(lang) == 100)
                result ++;
        return result;
    }

    public int getTotalErrors() {
        int result = 0;
        for (int i=0; i<progresses.size(); i++)
            result += progresses.get(i).getErrors();
        return result;
    }

    public int getStartedTopics() {return progresses.size();}

    public int getFinishedTopics() {
        int result = 0;
        for (ProgressTopic pr : progresses)
            if (pr.isCompleted())
                result ++;
        return result;
    }

    public String getAchievement() {
        if (isAdmin())
            return "Администратор";
        else {
            if (points < 1000)
                return "Новичок";
            else if (points < 5000)
                return "Скаут";
            else if (points < 10000)
                return "Продвинутый";
            else return "Профи";
        }
    }

    public String getFavouriteLanguage() {
        Map<String, Integer> langs = new HashMap<>();
        for (ProgressTopic p : progresses)
            if (p.isCompleted()) {
                if (!langs.containsKey(p.getTopic().getLanguage().getName()))
                    langs.put(p.getTopic().getLanguage().getName(), 1);
                else {
                    int add = langs.get(p.getTopic().getLanguage().getName());
                    langs.put(p.getTopic().getLanguage().getName(), add+1);
                }
            }
        return langs.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).findFirst().get().getKey();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTopics() {
        return topics;
    }

    public void setTopics(Integer topics) {
        this.topics = topics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<ProgressTopic> getProgresses() {
        return progresses;
    }

    public void setProgresses(List<ProgressTopic> progresses) {
        this.progresses = progresses;
    }
}
