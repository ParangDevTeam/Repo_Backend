package com.vowing.parang.service;

import com.vowing.parang.dto.MemberDTO;
import com.vowing.parang.entity.MemberEntity;
import com.vowing.parang.repository.MemberRepository;
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
