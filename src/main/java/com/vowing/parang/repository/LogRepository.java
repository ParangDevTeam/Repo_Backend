package com.vowing.parang.repository;

import com.vowing.parang.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findByCategoryId(Long categoryId);

    List<LogEntity> findByCreateLogTimeBetween(String fromDate, String toDate);
}
