package com.vowing.parang.domain.slot.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotCreateRequestDto {
    private Long id;
    private Long categoryId;
    private Long addNumber;
    private Long workDay;
    private String startDate;
}
