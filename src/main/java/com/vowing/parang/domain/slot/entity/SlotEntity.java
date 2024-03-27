package com.vowing.parang.domain.slot.entity;

import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.slot.dto.SlotDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AddSlot")
public class SlotEntity {

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

    public SlotDto toValueObject() {
        return new SlotDto(
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
