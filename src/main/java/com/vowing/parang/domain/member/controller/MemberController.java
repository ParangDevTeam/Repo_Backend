package com.vowing.parang.domain.member.controller;

import com.vowing.parang.domain.member.dto.MemberDto;
import com.vowing.parang.domain.member.dto.request.MemberUpdateRequestDto;
import com.vowing.parang.domain.member.dto.response.MemberResponseDto;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.error.UserNotFound;
import com.vowing.parang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자 추가
     *
     * @param dto 멤버 dto
     * */
    @PostMapping("/save")
    public ResponseEntity<MemberDto> memberSave(
            @RequestBody MemberDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.memberSave(dto));
    }

    /**
     * 전체 페이지 갯수 조회
     * */
    @GetMapping("/totalUser")
    public ResponseEntity<Long> getTotalCnt(@RequestParam(required = true, value = "agencyId") Long agencyId,@RequestParam(required = false, value = "userId") String userId) {
        long userCount = memberService.getAllUser(agencyId,userId).size();
        return ResponseEntity.status(HttpStatus.OK).body(userCount);
    }


    /**
     * 사용자 조회
     *
     * @param pageNo 페이지 번호
     * @param pageSize 페이지 당 상품 수
     * @param agencyId 대행사 id
     * @param userId 검색 사용자 id
     * @return 사용자 리스트
     * @throws UserNotFound 사용자가 존재하지 않음
     * */
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getUsers(@RequestParam(required = false,defaultValue = "1", value="page") int pageNo,
                                                        @RequestParam(required = false,defaultValue = "5", value="pageSize") int pageSize,
                                                        @RequestParam(required = false,value="userId") String userId,
                                                        @RequestParam(required = true, value="agencyId") Long agencyId
    ){

        List<MemberResponseDto> res = memberService.getByAgencyId(agencyId,pageNo,pageSize,userId);

        if(res.isEmpty()){
            throw new UserNotFound();
        }

        return ResponseEntity.ok().body(res);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody MemberUpdateRequestDto member) {

        memberService.updateMember(id,member);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
