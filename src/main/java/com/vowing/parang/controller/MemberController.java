package com.vowing.parang.controller;

import com.vowing.parang.dto.MemberDTO;
import com.vowing.parang.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
