package com.vowing.parang.domain.member.service;

import com.vowing.parang.domain.member.dto.MemberDto;
import com.vowing.parang.domain.member.dto.request.MemberUpdateRequestDto;
import com.vowing.parang.domain.member.dto.response.MemberResponseDto;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.error.UserNotFound;
import com.vowing.parang.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 사용자 추가
     *
     * @param dto 멤버 dto
     * */
    @Transactional
    public MemberDto memberSave(MemberDto dto) {
        final var entity = new MemberEntity();
        entity.setUserId(dto.getUserId());
        entity.setPassword(hashPassword(dto.getPassword()));
        entity.setMemo(dto.getMemo());
        entity.setUserType(dto.getUserType());
        entity.setAgencyId(dto.getAgencyId());
        return memberRepository.save(entity).toValueObject();
    }

    /**
     * 대행사 별 사용자 (페이징)
     * */
    @Transactional
    public List<MemberResponseDto> getByAgencyId(Long agencyId,int pageNo, int pageSize, String userId){

        if (agencyId == null) {
            throw new IllegalArgumentException("AgencyId cannot be null");
        }

        Pageable pageable = PageRequest.of(pageNo-1,pageSize,Sort.by("id").ascending());
        Page<MemberEntity> page = memberRepository.findMemberEntitiesByAgencyId(agencyId,pageable);

        List<MemberResponseDto> list = page.getContent().stream()
                .map(entity-> new MemberResponseDto(
                entity.getId(),
                entity.getUserId(),
                entity.getPassword(),
                entity.getMemo(),
                        true,
                        true

        )).toList();

        System.out.print(userId);

        //사용자 이름 검색 조회 시
        if(!userId.equals("null")){

            List<MemberResponseDto> filteredList = new ArrayList<>();
            for (MemberResponseDto member: list
                 ) {
                if(member.getUserId().contains(userId)){
                    filteredList.add(member);
                }
            }

            return filteredList;
        }

        return list;
    }

    /**
     * 전체 사용자
     * */
    @Transactional
    public List<MemberDto> getAllUser(Long agencyId,String userId){
        List<MemberDto> list = memberRepository.findAllByAgencyId(agencyId).stream().map(memberEntity -> new MemberDto(
                memberEntity.getId(),
                memberEntity.getUserId(),
                memberEntity.getPassword(),
                memberEntity.getMemo(),
                memberEntity.getUserType(),
                memberEntity.getAgencyId()
        )).toList();

        System.out.println(userId);

        //사용자 이름 검색 조회 시
        if(!userId.equals("null")){

            List<MemberDto> filteredList = new ArrayList<>();
            for (MemberDto member: list
            ) {
                if(member.getUserId().contains(userId)){
                    filteredList.add(member);
                }
            }

            return filteredList;
        }

        return list;
    }

    /**
     * 사용자 수정
     * */
    @Transactional
    public void updateMember(Long id, MemberUpdateRequestDto requestDto){

        Optional<MemberEntity> memberOptional = memberRepository.findById(id);

        if(memberOptional.isEmpty()){
            throw new UserNotFound();
        }

        MemberEntity existingMember = memberOptional.get();
        System.out.println(requestDto);
        System.out.println(requestDto.getMemo());
        System.out.println(requestDto.getPassword());

        if(!requestDto.getMemo().isEmpty())
            existingMember.setMemo(requestDto.getMemo());
        if(!requestDto.getPassword().isEmpty())
            existingMember.setPassword(hashPassword(requestDto.getPassword()));

        memberRepository.save(existingMember);
    }

    /**
     * 비밀번호 해시
     */
    @SneakyThrows(NoSuchAlgorithmException.class)
    private String hashPassword(String password) {
        final var instance = MessageDigest.getInstance("SHA-512");
        instance.update((password + "<JH: place your salt here>").getBytes(StandardCharsets.UTF_8));
        final var bytes = instance.digest();
        final var numericRepresentation = new BigInteger(1, bytes);

        return String.format("%0128x", numericRepresentation);
    }
}
