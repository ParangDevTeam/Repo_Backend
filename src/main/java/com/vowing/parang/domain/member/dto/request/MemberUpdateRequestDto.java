package com.vowing.parang.domain.member.dto.request;

import lombok.Data;

@Data
public class MemberUpdateRequestDto {

    private String password;
    private String memo;
}
