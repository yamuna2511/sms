package com.sa.socialcoding.sms.model;




import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DOB")
    private java.sql.Date dob;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "MAIL_ID")
    private String mailId;

    @Column(name = "CREATED_ON")
    private java.util.Date createdOn;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserCredentials userCredential;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, optional = true)
    @PrimaryKeyJoinColumn
    private ParentDetail parentDetail;

}
