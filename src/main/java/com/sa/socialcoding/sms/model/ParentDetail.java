package com.sa.socialcoding.sms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PARENT_DETAILS")
public class ParentDetail implements Serializable {
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "STUDENT_ID")
    private User student;

    @Column(name = "PARENT_FIRST_NAME")
    private String firstName;

    @Column(name = "PARENT_MIDDLE_NAME")
    private String middleName;

    @Column(name = "PARENT_LAST_NAME")
    private String lastname;

    @Column(name = "RELATION_TO_STUDENT")
    private String relationType;

    @Column(name = "PARENT_MOBILE")
    private String mobile;

    @Column(name = "PARENT_MAIL_ID")
    private String mailId;

}
