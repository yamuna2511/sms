package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="question_choice")
public class QuestionChoice {

    @Id
    @GeneratedValue
    @Column(name = "S_NO")
    private int rowId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "QUESTION_ID")
    @ToString.Exclude
    private Question question;

    @Column(name = "CHOICE")
    private String choice;

}
