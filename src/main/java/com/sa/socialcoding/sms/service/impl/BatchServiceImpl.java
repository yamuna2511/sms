package com.sa.socialcoding.sms.service.impl;

import com.sa.socialcoding.sms.dto.*;
import com.sa.socialcoding.sms.model.*;
import com.sa.socialcoding.sms.repository.*;
import com.sa.socialcoding.sms.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class BatchServiceImpl implements BatchService {

    @Autowired
    private ModuleTeacherAssignmentRepository moduleTeacherAssignmentRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private StudentBatchRepository studentBatchRepository;

    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    private StudentAssessmentRepository studentAssessmentRepository;

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

    @Override
    public String createAssignment(AssignmentDTO request) throws ParseException {
        Assignment ass = new Assignment();
        ass.setAssignmentType(request.getAssignmentType());
        ass.setTeacherId(request.getTeacherId());
        ass.setModuleId(request.getModuleId());
        ass.setDueDate(formatDate(request.getDueDate()));
        ass.setTopicId(request.getTopicId());
        ass.setQuestionSet(toQuestionEntity(request.getQuestionDTOList(), ass));
        log.info("Assignment Info - {}", ass);
        Assignment result = assignmentRepository.save(ass);
        return createStudentAssignment(result, request);
    }

    @Override
    public List<AssignmentDTO> getAllAssignments() {
        List<Assignment> assignmentList = assignmentRepository.findAll();
        List<AssignmentDTO> assignmentDTOList = new ArrayList<>();
        for(Assignment assignment : assignmentList) {
            AssignmentDTO dto = new AssignmentDTO();
            dto.setAssignmentType(assignment.getAssignmentType());
            dto.setDueDate(assignment.getDueDate().toString());
            dto.setTopicId(assignment.getTopicId());
            dto.setTeacherId(assignment.getTeacherId());
            dto.setModuleId(assignment.getModuleId());
            dto.setQuestionDTOList(toQuestionDTO(assignment.getQuestionSet()));
            assignmentDTOList.add(dto);
        }
        return assignmentDTOList;
    }

    private List<QuestionDTO> toQuestionDTO(Set<Question> questionSet) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question : questionSet) {
            QuestionDTO dto = new QuestionDTO();
            dto.setDesc(question.getDesc());
            dto.setType(question.getType());
            dto.setLink(question.getLink());
            dto.setChoiceDTOList(toQuestionChoiceDTO(question.getQuestionChoiceSet()));
            questionDTOList.add(dto);
        }
        return questionDTOList;
    }

    private List<QuestionChoiceDTO> toQuestionChoiceDTO(Set<QuestionChoice> questionChoiceSet) {
        List<QuestionChoiceDTO> questionChoiceDTOList = new ArrayList<>();
        for(QuestionChoice choice : questionChoiceSet) {
            QuestionChoiceDTO dto = new QuestionChoiceDTO();
            dto.setChoice(choice.getChoice());
            questionChoiceDTOList.add(dto);
        }
        return questionChoiceDTOList;
    }

    private String createStudentAssignment(Assignment result, AssignmentDTO request) {
        //1. get moduleId & TeacherId and get the batchId details
        //2. get studentBatchId via batchId
        //3. make entry in studentAssignment table
        int batchId= moduleTeacherAssignmentRepository.
                findByTeacherAndModuleId(request.getTeacherId(), request.getModuleId());
        Batch batch = new Batch();
        batch.setBatchId(batchId);
        List<Integer> studentBatchIds = studentBatchRepository.findByBatchId(batch);
        List<StudentAssignment> studentAssignmentList = new ArrayList<>();
        for(int studentBatchId : studentBatchIds) {
            StudentAssignment ass = new StudentAssignment();
            ass.setAssignedOn(new Date());
            ass.setAssignmentId(result.getAssignmentId());
            ass.setStudentBatchId(studentBatchId);
            ass.setModuleId(request.getModuleId());
            ass.setTopicId(request.getTopicId());
            ass.setDueDate(result.getDueDate());
            ass.setTeacherId(request.getTeacherId());
            studentAssignmentList.add(ass);
        }
        studentAssignmentRepository.saveAll(studentAssignmentList);
        return "Created successfully";
    }

    private Set<Question> toQuestionEntity(List<QuestionDTO> questionDTOList, Assignment ass) {
        Set<Question> questionSet = new HashSet<>();
        for(QuestionDTO questionDTO : questionDTOList) {
            Question ques = new Question();
            ques.setAssignment(ass);
            ques.setDesc(questionDTO.getDesc());
            ques.setLink(questionDTO.getLink());
            ques.setType(questionDTO.getType());
            if(questionDTO.getChoiceDTOList() != null)
                ques.setQuestionChoiceSet(toQuestionChoiceEntity(questionDTO.getChoiceDTOList(), ques));
            questionSet.add(ques);
        }
        return questionSet;
    }

    private Set<QuestionAsmt> toQuestionAsmtEntity(List<QuestionDTO> questionDTOList, Assessment ass) {
        Set<QuestionAsmt> questionSet = new HashSet<>();
        for(QuestionDTO questionDTO : questionDTOList) {
            QuestionAsmt ques = new QuestionAsmt();
            ques.setAssessment(ass);
            ques.setDesc(questionDTO.getDesc());
            ques.setLink(questionDTO.getLink());
            ques.setType(questionDTO.getType());
            if(questionDTO.getChoiceDTOList() != null)
                ques.setQuestionChoiceSet(toQuestionAsmtChoiceEntity(questionDTO.getChoiceDTOList(), ques));
            questionSet.add(ques);
        }
        return questionSet;
    }

    private Set<QuestionChoice> toQuestionChoiceEntity(List<QuestionChoiceDTO> choiceDTOList, Question ques) {
        Set<QuestionChoice> questionChoiceSet = new HashSet<>();
        for (QuestionChoiceDTO questionChoiceDTO : choiceDTOList) {
            QuestionChoice choice = new QuestionChoice();
            choice.setQuestion(ques);
            choice.setChoice(questionChoiceDTO.getChoice());
            questionChoiceSet.add(choice);
        }
        return questionChoiceSet;
    }

    private Set<QuestionAsmtChoice> toQuestionAsmtChoiceEntity(List<QuestionChoiceDTO> choiceDTOList, QuestionAsmt ques) {
        Set<QuestionAsmtChoice> questionChoiceSet = new HashSet<>();
        for (QuestionChoiceDTO questionChoiceDTO : choiceDTOList) {
            QuestionAsmtChoice choice = new QuestionAsmtChoice();
            choice.setQuestion(ques);
            choice.setChoice(questionChoiceDTO.getChoice());
            questionChoiceSet.add(choice);
        }
        return questionChoiceSet;
    }

    private Date formatDate(String dueDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return formatter.parse(dueDate);
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
        cal.add(Calendar.MONTH, duration);
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

    @Override
    public String createAssessment(AssessmentDTO request) throws ParseException {
        Assessment ass = new Assessment();
        ass.setModuleId(request.getModuleId());
        ass.setTeacherId(request.getTeacherId());
        ass.setAssessmentDate(formatDate(request.getAssessmentDate()));
        ass.setMarks(request.getMarks());
        ass.setAssessmentName(request.getAssessmentName());
        ass.setQuestionSet(toQuestionAsmtEntity(request.getQuestionDTOList(), ass));

        log.info("Assessment Info - {}", ass);
        Assessment result = assessmentRepository.save(ass);
        return createStudentAssessment(result, request);
    }

    private String createStudentAssessment(Assessment result, AssessmentDTO request) {
        //1. get moduleId & TeacherId and get the batchId details
        //2. get studentBatchId via batchId
        //3. make entry in studentAssignment table
        int batchId= moduleTeacherAssignmentRepository.
                findByTeacherAndModuleId(request.getTeacherId(), request.getModuleId());
        Batch batch = new Batch();
        batch.setBatchId(batchId);
        List<Integer> studentBatchIds = studentBatchRepository.findByBatchId(batch);
        List<StudentAssessment> studentAssessmentList = new ArrayList<>();
        for(int studentBatchId : studentBatchIds) {
            StudentAssessment ass = new StudentAssessment();
            ass.setAssessmentId(result.getAssessmentId());
            ass.setStudentBatchId(studentBatchId);
            ass.setModuleId(request.getModuleId());
            ass.setTeacherId(request.getTeacherId());
            ass.setAssignedDate(new Date());
            studentAssessmentList.add(ass);
        }
        studentAssessmentRepository.saveAll(studentAssessmentList);
        return "Created successfully";
    }
}
