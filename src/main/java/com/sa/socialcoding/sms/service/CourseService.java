package com.sa.socialcoding.sms.service;

import com.sa.socialcoding.sms.model.Course;
import com.sa.socialcoding.sms.model.Module;

public interface CourseService {
    public Course findByCourseId(int courseId);

    String createCourse(Course courseRequest);

    String createTopic(Module moduleDetails);

    public Module findByModuleId(int moduleId);
}
