package com.vowing.parang.domain.member.dto;

import com.vowing.parang.domain.member.constant.UserType;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String userId;
    private String password;
    private String memo;
    private UserType userType;
    private Long agencyId;


//    public static reggdsrg() {
//        memberentity = (
//
//                )
//    }

}
