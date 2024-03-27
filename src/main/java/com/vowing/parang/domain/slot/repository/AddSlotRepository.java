package com.vowing.parang.domain.slot.repository;

import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.slot.entity.AddSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddSlotRepository extends JpaRepository<AddSlotEntity, Long> {
}
