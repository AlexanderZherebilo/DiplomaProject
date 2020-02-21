package com.springproject.ZherebiloAV.domain;

import javax.persistence.*;

@Entity
@Table(name = "optin")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    public Option() {
    }

    public Option(String name) {
        this.name = name;
    }

    public Option(String name, Question question) {
        this.name = name;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
