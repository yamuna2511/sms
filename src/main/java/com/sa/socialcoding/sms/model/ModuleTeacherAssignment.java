package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="module_teacher_assignment")
public class ModuleTeacherAssignment {
    @Id
    @GeneratedValue
    @Column(name = "MAPPING_ID")
    private int mappingId;
    @Column(name = "BATCH_ID")
    private int batchId;
    @Column(name = "MODULE_ID")
    private int moduleId;
    @Column(name = "TEACHER_ID")
    private int teacherId;
}
