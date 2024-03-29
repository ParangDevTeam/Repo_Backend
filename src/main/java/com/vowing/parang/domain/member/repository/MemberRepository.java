package com.vowing.parang.domain.member.repository;

import com.vowing.parang.domain.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUserId(String userId);
    Page<MemberEntity> findMemberEntitiesByAgencyId(Long agencyId, Pageable pageable);
    List<MemberEntity> findAllByAgencyId(Long agencyId);
}
