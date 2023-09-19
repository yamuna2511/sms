package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name="batch")
public class Batch {
    @Id
    @GeneratedValue
    @Column(name = "BATCH_ID")
    private int batchId;
    @Column(name = "COURSE_ID")
    private int courseId;
    @Column(name = "BATCH_NAME")
    private String batchName;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentBatch> studentBatches = new HashSet<>();
}
