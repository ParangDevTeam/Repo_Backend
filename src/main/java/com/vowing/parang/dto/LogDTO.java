package com.vowing.parang.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDTO {
    private Long id;
    private LocalDateTime timestamp;
    private String userId;
    private String status;
    private Long workDay;
    private Long addNumber;
    private Long categoryId;
}
