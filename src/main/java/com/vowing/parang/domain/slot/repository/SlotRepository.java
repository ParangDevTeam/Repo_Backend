package com.vowing.parang.domain.slot.repository;

import com.vowing.parang.domain.slot.entity.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {
}
