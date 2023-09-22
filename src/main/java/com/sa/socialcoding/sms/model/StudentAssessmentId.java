package com.sa.socialcoding.sms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentAssessmentId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int studentBatchId;
    private int moduleId;
    private int teacherId;
    private String assessmentId;
}
