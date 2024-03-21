package com.vowing.parang.repository;

import com.vowing.parang.entity.AddSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddSlotRepository extends JpaRepository<AddSlotEntity, Long> {
}
