package com.vowing.parang.domain.refund.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.refund.dto.RefundDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Refund")
public class RefundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private MemberEntity userId;

    @Column
    private String productName;
    @Column
    private String productMid;
    @Column
    private String storeName;
    @Column
    private String startDate;
    @Column
    private String refundTime;

    public RefundDTO toValueObject() {
        return new RefundDTO(
                this.getId(),
                this.getCategory(),
                this.getUserId().getUserId(),
                this.getProductName(),
                this.getStoreName(),
                this.getStartDate(),
                this.getRefundTime()
        );
    }
}
