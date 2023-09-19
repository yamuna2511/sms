package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="student_batch")
@IdClass(StudentBatchId.class)
public class StudentBatch {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_BATCH_ID")
    private int studentBatchId;

    @Column(name = "STUDENT_ID")
    private int studentId;

    @Column(name = "ATTENDANCE")
    private String attendance;

    @Column(name = "COMPLETION_STATUS")
    private String completionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BATCH_ID", referencedColumnName = "BATCH_ID", nullable = false)
    @ToString.Exclude
    private Batch batch;
}
