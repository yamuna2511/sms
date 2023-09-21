package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private int questionId;

    @Column(name = "QUESTION_DESC")
    private String desc;

    @Column(name = "QUESTION_TYPE")
    private String type;

    @Column(name = "PAGE_LINK")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASS_QUES_ID", nullable = false)
    @ToString.Exclude
    private Assignment assignment;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionChoice> questionChoiceSet = new HashSet<>();
}
