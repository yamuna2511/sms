package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
}
