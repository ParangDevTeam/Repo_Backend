package com.vowing.parang.domain.refund.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.repository.MemberRepository;
import com.vowing.parang.domain.refund.dto.RefundDTO;
import com.vowing.parang.domain.slot.entity.SlotEntity;
import com.vowing.parang.domain.slot.repository.SlotRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

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
                this.getProductMid(),
                this.getStartDate(),
                this.getRefundTime()
        );
    }

    /**
     * 클라이언트 SlotDto -> Entity로 변환
     */
    public static RefundEntity fromSlotEntity(SlotEntity slotEntity) {
        final var refundEntity = new RefundEntity();
        refundEntity.setCategory(slotEntity.getCategory().getCategory());
        refundEntity.setUserId(slotEntity.getUserId());
        refundEntity.setProductName(slotEntity.getProductName());
        refundEntity.setProductMid(slotEntity.getProductMid());
        refundEntity.setStartDate(slotEntity.getStartDate());
        refundEntity.setRefundTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return refundEntity;
    }
}
