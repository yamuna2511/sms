package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_CREDENTIALS")
public class UserCredentials implements Serializable {
    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LAST_LOGIN_DT")
    private java.sql.Date lastLoginDate;

    @Column(name = "USER_STATUS")
    private String userStatus;


}
