package com.vowing.parang.domain.member.entity;

import com.vowing.parang.domain.member.constant.UserType;
import com.vowing.parang.domain.member.dto.MemberDto;
import com.vowing.parang.domain.member.dto.response.MemberResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Member")
public class MemberEntity {

    /**
     * 회원 번호(PK)
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "memo", nullable = false)
    private String memo;
    @Column(name="userType",nullable = false)
    private UserType userType;
    @Column(name="agencyId",nullable = true)
    private Long agencyId;

    /**
     * 생성자
     * */
    public MemberDto toValueObject() {
        return new MemberDto(
                this.getId(),
                this.getUserId(),
                this.getPassword(),
                this.getMemo(),
                this.getUserType(),
                this.getAgencyId()
        );
    }

}
