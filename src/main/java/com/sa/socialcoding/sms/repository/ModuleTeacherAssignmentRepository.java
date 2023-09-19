package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.ModuleTeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleTeacherAssignmentRepository extends JpaRepository<ModuleTeacherAssignment, Integer> {
    @Query("select m from ModuleTeacherAssignment m where m.teacherId=?1")
    List<ModuleTeacherAssignment> findByTeacherId(int teacherId);
}
