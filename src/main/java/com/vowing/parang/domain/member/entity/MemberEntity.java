package com.vowing.parang.domain.member.entity;

import com.vowing.parang.domain.member.dto.MemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public MemberDTO toValueObject() {
        return new MemberDTO(
                this.getId(),
                this.getUserId(),
                this.getPassword(),
                this.getMemo()
        );
    }
}
