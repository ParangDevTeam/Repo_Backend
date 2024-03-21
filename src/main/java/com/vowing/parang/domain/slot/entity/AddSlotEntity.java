package com.vowing.parang.domain.slot.entity;

import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.slot.dto.AddSlotDTO;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AddSlot")
public class AddSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 설정
    private MemberEntity userId; // MemberEntity와의 관계 설정

    @Column
    private Long addNumber;
    @Column
    private Long workDay;
    @Column
    private String startDate;
    @Column
    private String productName;
    @Column
    private String productMid;
    @Column
    private String mainKeyword;
    @Column
    private String serveKeyword;
    @Column
    private String goShopping;
    @Column
    private String storeName;
    @Column
    private String endDate;
    @Column
    private String productUrl;
    @Column
    private String link;

    public AddSlotDTO toValueObject() {
        return new AddSlotDTO(
                this.getId(),
                this.getCategory().getId(),
                this.getUserId().getUserId(),
                this.getAddNumber(),
                this.getWorkDay(),
                this.getStartDate(),
                this.getProductName(),
                this.getProductMid(),
                this.getMainKeyword(),
                this.getServeKeyword(),
                this.getGoShopping(),
                this.getStoreName(),
                this.getEndDate(),
                this.getProductUrl(),
                this.getLink()
        );
    }
}
