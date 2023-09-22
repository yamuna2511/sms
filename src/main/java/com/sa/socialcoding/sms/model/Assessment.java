package com.sa.socialcoding.sms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name="assessment")
public class Assessment {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.sa.socialcoding.sms.model.AssessmentIdGenerator")
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;

    @Column(name = "TEACHER_ID")
    private int teacherId;

    @Column(name = "MODULE_ID")
    private int moduleId;

    @Column(name = "ASSESSMENT_NAME")
    private String assessmentName;

    @Column(name = "ASSESSMENT_DATE")
    private Date assessmentDate;

    @Column(name = "MARKS")
    private int marks;

    @OneToMany(mappedBy = "assessment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionAsmt> questionSet = new HashSet<>();
}
