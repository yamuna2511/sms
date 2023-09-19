package com.sa.socialcoding.sms.repository;


import com.sa.socialcoding.sms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

   Course findByCourseId(int courseId);
}
