package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name="student_assignment")
@IdClass(StudentAssignmentId.class)
public class StudentAssignment {

    @Id
    @Column(name = "STUDENT_BATCH_ID")
    private int studentBatchId;

    @Id
    @Column(name = "MODULE_ID")
    private int moduleId;

    @Id
    @Column(name = "TOPIC_ID")
    private int topicId;

    @Column(name = "DATE_ASSIGNED")
    private Date assignedOn;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "COMPLETION_STATUS")
    private String completionStatus;

    @Id
    @Column(name = "ASSIGNMENT_ID")
    private String assignmentId;
}
