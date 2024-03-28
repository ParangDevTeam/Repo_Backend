package com.vowing.parang.domain.refund.entity;

import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.refund.dto.RefundDTO;
import com.vowing.parang.domain.slot.entity.SlotEntity;
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
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private MemberEntity userId;

    @Column(name = "productName")
    private String productName;
    @Column(name = "productMid")
    private String productMid;
    @Column(name = "storeName")
    private String storeName;
    @Column(name = "startDate")
    private String startDate;
    @Column(name = "refundTime")
    private String refundTime;
    
    /**
     *  생성자
     */
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
     * 환불 요청 시 환불 테이블 입력
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
