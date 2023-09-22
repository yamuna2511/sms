package com.sa.socialcoding.sms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="question_choice")
public class QuestionAsmtChoice {
    @Id
    @GeneratedValue
    @Column(name = "S_NO")
    private int rowId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "QUESTION_ID")
    @ToString.Exclude
    private QuestionAsmt question;

    @Column(name = "CHOICE")
    private String choice;

}
