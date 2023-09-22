package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.StudentAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssessmentRepository extends JpaRepository<StudentAssessment, String> {
}
