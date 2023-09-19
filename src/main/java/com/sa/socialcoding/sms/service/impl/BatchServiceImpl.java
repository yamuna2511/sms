package com.sa.socialcoding.sms.service.impl;

import com.sa.socialcoding.sms.dto.BatchDTO;
import com.sa.socialcoding.sms.dto.ModuleTeacherAssignmentDTO;
import com.sa.socialcoding.sms.dto.StudentBatchDTO;
import com.sa.socialcoding.sms.model.Batch;
import com.sa.socialcoding.sms.model.ModuleTeacherAssignment;
import com.sa.socialcoding.sms.model.StudentBatch;
import com.sa.socialcoding.sms.repository.BatchRepository;
import com.sa.socialcoding.sms.repository.ModuleTeacherAssignmentRepository;
import com.sa.socialcoding.sms.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BatchServiceImpl implements BatchService {

    @Autowired
    private ModuleTeacherAssignmentRepository moduleTeacherAssignmentRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private CourseServiceImpl courseService;

    @Override
    public String assignModule(ModuleTeacherAssignmentDTO request) throws Exception {
        ModuleTeacherAssignment moduleTeacherAssignment = new ModuleTeacherAssignment();
        moduleTeacherAssignment.setModuleId(request.getModuleId());
        moduleTeacherAssignment.setBatchId(request.getBatchId());
        moduleTeacherAssignment.setTeacherId(request.getTeacherId());
        moduleTeacherAssignmentRepository.save(moduleTeacherAssignment);
        return "Assigned successfully";
    }

    @Override
    public List<ModuleTeacherAssignmentDTO> getAllAssignedModules(int teacherId) {
        List<ModuleTeacherAssignment> moduleList = moduleTeacherAssignmentRepository.findByTeacherId(teacherId);
        List<ModuleTeacherAssignmentDTO> moduleTeacherAssignmentDTOList = new ArrayList<>();
        for (ModuleTeacherAssignment module : moduleList) {
            ModuleTeacherAssignmentDTO moduleTeacherAssignmentDTO = new ModuleTeacherAssignmentDTO();
            moduleTeacherAssignmentDTO.setBatchId(module.getBatchId());
            moduleTeacherAssignmentDTO.setModuleId(module.getModuleId());
            moduleTeacherAssignmentDTO.setTeacherId(module.getTeacherId());
            moduleTeacherAssignmentDTOList.add(moduleTeacherAssignmentDTO);
        }
        return moduleTeacherAssignmentDTOList;
    }
    @Override
    public BatchDTO getBatchDetails(int batchId) {
        Optional<Batch> batch = batchRepository.findById(batchId);
        return toBatchDTO(batch.get());
    }

    @Override
    public List<BatchDTO> getAllBatches() {
        List<Batch> batchList = batchRepository.findAll();
        List<BatchDTO> batchDTOList = new ArrayList<>();
        for (Batch batch : batchList) {
            batchDTOList.add(toBatchDTO(batch));
        }
        return batchDTOList;
    }

    private BatchDTO toBatchDTO(Batch batch) {
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setBatchName(batch.getBatchName());
        batchDTO.setBatchId(batch.getBatchId());
        batchDTO.setStudentBatchList(toStudentBatchDTO(batch.getStudentBatches()));
        batchDTO.setEndDate(batch.getEndDate().toString());
        batchDTO.setStartDate(batch.getStartDate().toString());
        batchDTO.setCourseId(batch.getCourseId());
        return batchDTO;
    }

    private List<StudentBatchDTO> toStudentBatchDTO(Set<StudentBatch> studentBatches) {
        List<StudentBatchDTO> studentBatchDTOList = new ArrayList<>();
        for (StudentBatch studentBatch : studentBatches) {
            StudentBatchDTO dto = new StudentBatchDTO();
            dto.setStudentId(studentBatch.getStudentId());
            dto.setAttendance(studentBatch.getAttendance());
            dto.setCompletionStatus(studentBatch.getCompletionStatus());
            studentBatchDTOList.add(dto);
        }
        return studentBatchDTOList;
    }

    @Override
    public String createBatch(BatchDTO request) {
        Batch batch = new Batch();
        batch.setBatchName(request.getBatchName());
        batch.setCourseId(request.getCourseId());
        batch.setStudentBatches(toStudentBatchEntity(request.getStudentBatchList(), batch));
        batch.setStartDate(new Date());
        Date endDate = getEndDate(request.getCourseId());
        batch.setEndDate(endDate);
        log.info("Batch Info - {}", batch);
        batchRepository.save(batch);
        return "Created successfully";
    }

    private Date getEndDate(int courseId) {
        Calendar cal = Calendar.getInstance();
        int duration = courseService.findByCourseId(courseId).getDurationInMonth();
        cal.add(Calendar.MONTH, duration);//get from course details API
        return cal.getTime();
    }

    private Set<StudentBatch> toStudentBatchEntity(List<StudentBatchDTO> studentBatchList, Batch batch) {
        Set<StudentBatch> studentBatches = new HashSet<>();
        for (StudentBatchDTO dto : studentBatchList) {
            StudentBatch batchEntity = new StudentBatch();
            batchEntity.setStudentId(dto.getStudentId());
            batchEntity.setAttendance(dto.getAttendance());
            batchEntity.setCompletionStatus(dto.getCompletionStatus());
            batchEntity.setBatch(batch);
            studentBatches.add(batchEntity);
        }
        return studentBatches;
    }
}
