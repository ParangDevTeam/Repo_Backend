package com.vowing.parang.domain.member.service;

import com.vowing.parang.domain.member.dto.MemberDTO;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDTO memberSave(MemberDTO dto) {
        final var entity = new MemberEntity();
        entity.setUserId(dto.getUserId());
        entity.setPassword(dto.getPassword());
        entity.setMemo(dto.getMemo());
        return memberRepository.save(entity).toValueObject();
    }
}
