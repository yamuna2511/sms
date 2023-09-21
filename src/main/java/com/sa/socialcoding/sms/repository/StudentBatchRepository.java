package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.Batch;
import com.sa.socialcoding.sms.model.StudentBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentBatchRepository extends JpaRepository<StudentBatch, Integer> {
    @Query("select m.studentBatchId from StudentBatch m where m.batch=?1")
    List<Integer> findByBatchId(Batch batch);
}
