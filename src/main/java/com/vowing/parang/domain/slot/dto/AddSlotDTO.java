package com.vowing.parang.domain.slot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddSlotDTO {
    private Long id;
    private Long categoryId;
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
