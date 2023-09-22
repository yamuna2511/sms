package com.sa.socialcoding.sms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name="student_assessment")
@IdClass(StudentAssessmentId.class)
public class StudentAssessment {

    @Id
    @Column(name = "STUDENT_BATCH_ID")
    private int studentBatchId;

    @Id
    @Column(name = "MODULE_ID")
    private int moduleId;

    @Id
    @Column(name = "TEACHER_ID")
    private int teacherId;

    @Column(name = "DATE_ASSIGNED")
    private Date assignedDate;

    @Column(name = "COMPLETION_STATUS")
    private String completionStatus;

    @Id
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
}
