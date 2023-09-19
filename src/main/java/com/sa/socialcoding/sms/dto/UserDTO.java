package com.sa.socialcoding.sms.dto;

import lombok.*;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String userType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String mobile;
    private String mailId;
    private Date createdOn;
    private ParentDetailDTO parentDetailDTO;
}
