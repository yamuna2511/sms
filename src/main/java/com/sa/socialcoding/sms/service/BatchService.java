package com.sa.socialcoding.sms.service;

import com.sa.socialcoding.sms.dto.AssignmentDTO;
import com.sa.socialcoding.sms.dto.BatchDTO;
import com.sa.socialcoding.sms.dto.ModuleTeacherAssignmentDTO;
import com.sa.socialcoding.sms.model.Batch;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface BatchService {
    String assignModule(ModuleTeacherAssignmentDTO request) throws Exception;
    List<ModuleTeacherAssignmentDTO> getAllAssignedModules(int teacherId);
    String createBatch(BatchDTO request);
    BatchDTO getBatchDetails(int batchId);
    List<BatchDTO> getAllBatches();
    String createAssignment(AssignmentDTO request) throws ParseException;
    List<AssignmentDTO> getAllAssignments();
}
