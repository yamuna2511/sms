package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
}
