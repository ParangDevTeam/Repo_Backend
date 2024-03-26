package com.vowing.parang.domain.member.dto.response;

import com.vowing.parang.domain.member.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String userId;
    private String password;
    private String memoContent;
    private Boolean fix;

    private Boolean slotAdd;

}
