package com.vowing.parang.domain.log.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDTO {
    private Long id;
    private String createLogTime;
    private String userId;
    private String status;
    private Long workDay;
    private Long addNumber;
    private String category;
}
