package com.vowing.parang.domain.refund.repository;

import com.vowing.parang.domain.refund.entity.RefundEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity, Long> {
    Page<RefundEntity> findByCategory(String category, Pageable pageable);
}
