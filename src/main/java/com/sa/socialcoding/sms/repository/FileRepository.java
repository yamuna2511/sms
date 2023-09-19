package com.sa.socialcoding.sms.repository;

import com.sa.socialcoding.sms.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    @Query(
            nativeQuery = true,
            value = "select * from File u where u.fileName = :fileName")
    Optional<File> findFileByFileName(@Param("fileName") String fileName);
}
