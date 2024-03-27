package com.vowing.parang.domain.log.repository;

import com.vowing.parang.domain.log.entity.LogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> findAllByCreateLogTimeBetweenAndCategory(String fromDate, String toDate, String category);
    Page<LogEntity> findByCreateLogTimeBetweenAndCategory(String fromDate, String toDate, String category, Pageable pageable);
}
