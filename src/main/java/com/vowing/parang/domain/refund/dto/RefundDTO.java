package com.vowing.parang.domain.refund.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefundDTO {
    private Long id;
    private String category;
    private String userId;
    private String productName;
    private String storeName;
    private String startDate;
    private String refundTime;
}
