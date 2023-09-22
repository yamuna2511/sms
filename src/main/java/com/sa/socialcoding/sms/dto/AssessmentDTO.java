package com.sa.socialcoding.sms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssessmentDTO {
    private int teacherId;
    private int moduleId;
    private String assessmentDate;
    private String assessmentName;
    private int marks;
    private List<QuestionDTO> questionDTOList;
}
