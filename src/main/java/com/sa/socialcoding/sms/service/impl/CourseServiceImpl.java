package com.sa.socialcoding.sms.service.impl;

import com.sa.socialcoding.sms.model.Course;
import com.sa.socialcoding.sms.model.Module;
import com.sa.socialcoding.sms.model.Topic;
import com.sa.socialcoding.sms.repository.CourseRepository;
import com.sa.socialcoding.sms.repository.ModuleRepository;
import com.sa.socialcoding.sms.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public Course findByCourseId(int courseId){

        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public String createCourse(Course courseReq) {
        Course course= new Course();
        course.setCourseName(courseReq.getCourseName());
        course.setCourseDescription(courseReq.getCourseDescription());
        course.setDurationInMonth(courseReq.getDurationInMonth());
        Set<Module> moduleSet=courseReq.getModule();
        if(Objects.nonNull(moduleSet)){
            for(Module module : moduleSet){
                course.getModule().add(module);
            }
        }
        courseRepository.save(course);
        return "course created successfully";
    }

    @Override
    public String createTopic(Module moduleDetails) {
        Module modules = new Module();
        modules.setModuleId(moduleDetails.getModuleId());
        modules.setModuleDescription(moduleDetails.getModuleDescription());
        modules.setModuleName(moduleDetails.getModuleName());
        Set<Topic> topicSet=moduleDetails.getTopic();
        if(Objects.nonNull(topicSet)){
            for(Topic topics : topicSet){
                modules.getTopic().add(topics);
            }
        }
        moduleRepository.save(modules);
        return "Topic created successfully";
    }

    @Override
    public Module findByModuleId(int moduleId){

        return moduleRepository.findByModuleId(moduleId);
    }
}
