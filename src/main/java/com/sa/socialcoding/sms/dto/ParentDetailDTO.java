package com.sa.socialcoding.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentDetailDTO {
    private String firstName;
    private String middleName;
    private String lastname;
    private String relationType;
    private String mobile;
    private String mailId;
}
