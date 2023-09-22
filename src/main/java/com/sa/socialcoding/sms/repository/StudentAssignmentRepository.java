package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Integer> {
}
