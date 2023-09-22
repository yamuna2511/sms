package com.sa.socialcoding.sms.service;

import com.sa.socialcoding.sms.dto.AssessmentDTO;
import com.sa.socialcoding.sms.dto.AssignmentDTO;
import com.sa.socialcoding.sms.dto.BatchDTO;
import com.sa.socialcoding.sms.dto.ModuleTeacherAssignmentDTO;

import java.text.ParseException;
import java.util.List;

public interface BatchService {
    String assignModule(ModuleTeacherAssignmentDTO request) throws Exception;
    List<ModuleTeacherAssignmentDTO> getAllAssignedModules(int teacherId);
    String createBatch(BatchDTO request);
    BatchDTO getBatchDetails(int batchId);
    List<BatchDTO> getAllBatches();
    String createAssignment(AssignmentDTO request) throws ParseException;
    List<AssignmentDTO> getAllAssignments();
    String createAssessment(AssessmentDTO request) throws ParseException;
}
