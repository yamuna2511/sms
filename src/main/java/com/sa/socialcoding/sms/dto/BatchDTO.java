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
public class BatchDTO {
    private int batchId;
    private int courseId;
    private String batchName;
    private String startDate;
    private String endDate;
    private List<StudentBatchDTO> studentBatchList;
}
