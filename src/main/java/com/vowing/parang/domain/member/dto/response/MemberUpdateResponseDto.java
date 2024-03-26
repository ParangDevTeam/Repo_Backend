package com.vowing.parang.domain.member.dto.response;

import com.vowing.parang.domain.member.constant.UserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberUpdateResponseDto {

    private Long id;
    private String userId;
    private String password;
    private String memo;
}
