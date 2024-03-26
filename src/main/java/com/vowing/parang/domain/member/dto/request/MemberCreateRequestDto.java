package com.vowing.parang.domain.member.dto.request;

import com.vowing.parang.domain.member.constant.UserType;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequestDto {

    @NotNull
    private Long id;

    @NotFound
    private String loginId;

    @NotNull
    private String password;

    @Nullable
    private String memo;

    @NotNull
    private UserType userType;
}
