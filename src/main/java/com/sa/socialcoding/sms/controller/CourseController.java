package com.sa.socialcoding.sms.controller;

import com.sa.socialcoding.sms.model.Course;
import com.sa.socialcoding.sms.model.Module;
import com.sa.socialcoding.sms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(path="/createCourse")
    public ResponseEntity<String> createCourse(
            @RequestHeader(HttpHeaders.ACCEPT) String language,
            @RequestBody Course courseReq){

        String msg = courseService.createCourse(courseReq);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(path="/getCourses")
    public Course getCourses(
            @RequestParam(name = "courseId", required = false) Integer courseId) {
        return courseService.findByCourseId(courseId);
    }

    @PostMapping(path="/createTopic")
    public ResponseEntity<String> createTopic(
            @RequestHeader(HttpHeaders.ACCEPT) String language,
            @RequestBody Module moduleDetails){

        String msg = courseService.createTopic(moduleDetails);
        return ResponseEntity.ok(msg);
    }

    @GetMapping(path="/getTopics")
    public Module getTopics(
            @RequestParam(name = "moduleId", required = false) Integer moduleId) {
        return courseService.findByModuleId(moduleId);
    }
}
