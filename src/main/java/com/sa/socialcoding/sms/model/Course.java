package com.sa.socialcoding.sms.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="COURSE")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private int courseId;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "DURATION_IN_MONTH")
    private int durationInMonth;

    @Column(name = "COURSE_DESCRIPTION")
    private String courseDescription;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    Set<Module> module = new HashSet<>();

}
