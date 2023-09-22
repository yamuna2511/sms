package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, String> {
}
