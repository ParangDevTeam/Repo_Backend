package com.vowing.parang.domain.member.controller;

import com.vowing.parang.domain.member.dto.MemberDTO;
import com.vowing.parang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("save")
    public ResponseEntity<MemberDTO> memberSave(
            @RequestBody MemberDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.memberSave(dto));
    }
}
