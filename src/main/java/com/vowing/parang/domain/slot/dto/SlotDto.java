package com.vowing.parang.domain.slot.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotDto {
    private Long id;
    private String category;
    private String userId;
    private Long addNumber;
    private Long workDay;
    private String startDate;
    private String productName;
    private String productMid;
    private String mainKeyword;
    private String serveKeyword;
    private String goShopping;
    private String storeName;
    private String endDate;
    private String productUrl;
    private String link;
}
