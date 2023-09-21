package com.sa.socialcoding.sms.controller;

import com.sa.socialcoding.sms.dto.AssignmentDTO;
import com.sa.socialcoding.sms.dto.BatchDTO;
import com.sa.socialcoding.sms.dto.ModuleTeacherAssignmentDTO;
import com.sa.socialcoding.sms.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/batch")
@Slf4j
public class BatchController {
    private static Logger logger = LoggerFactory.getLogger(BatchController.class);
    @Autowired
    private BatchService batchService;

    @PostMapping(path = "/teacher/assignModule")
    public ResponseEntity<String> moduleTeacherAssignment(
            @RequestBody ModuleTeacherAssignmentDTO request){
        try {
           String result = batchService.assignModule(request);
           return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Exception while assigning course to teacher {}", e.getCause());
            return (ResponseEntity<String>) ResponseEntity.internalServerError();
        }
    }

    @GetMapping(path = "/teacher/allModules")
    public List<ModuleTeacherAssignmentDTO> getAllAssignedModules(
            @RequestParam(name = "userId", required = false) Integer teacherId) {
        return batchService.getAllAssignedModules(teacherId.intValue());
    }

    @PostMapping(path = "/submit")
    public ResponseEntity<String> createBatch(
            @RequestBody BatchDTO request){
        try {
            String result = batchService.createBatch(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Exception while creating batch {}", e.getCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/detail")
    public BatchDTO getBatchDetails(
            @RequestParam(name = "batchId", required = false) int batchId) {
        return batchService.getBatchDetails(batchId);
    }

    @GetMapping(path = "/all")
    public List<BatchDTO> getAllBatches() {
        return batchService.getAllBatches();
    }

    @PostMapping(path = "/submit/assignment")
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentDTO request) {
        try {
            String result = batchService.createAssignment(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Exception while creating assignment {}", e.getCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/allAssignments")
    public List<AssignmentDTO> getAllAssignments() {
        return batchService.getAllAssignments();
    }
}
