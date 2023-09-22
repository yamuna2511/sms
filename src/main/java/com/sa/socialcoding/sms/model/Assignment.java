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
@Table(name="assignment")
public class Assignment {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.sa.socialcoding.sms.model.AssignmentIdGenerator")
    @Column(name = "ASSIGNMENT_ID")
    private String assignmentId;

    @Column(name = "TEACHER_ID")
    private int teacherId;

    @Column(name = "MODULE_ID")
    private int moduleId;

    @Column(name = "TOPIC_ID")
    private int topicId;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "ASSIGNMENT_TYPE")
    private String assignmentType;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questionSet = new HashSet<>();
}
