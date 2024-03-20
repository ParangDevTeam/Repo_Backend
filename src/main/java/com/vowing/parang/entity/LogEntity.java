package com.vowing.parang.entity;

import com.vowing.parang.dto.LogDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "LogView")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;
    @Column
    private String userId;
    @Column
    private String status;
    @Column
    private Long workDay;
    @Column
    private Long addNumber;
    @Column
    private Long categoryId;

    public LogDTO toValueObject() {
        return new LogDTO(
                this.getId(),
                this.getTimestamp(),
                this.getUserId(),
                this.getStatus(),
                this.getWorkDay(),
                this.getAddNumber(),
                this.getCategoryId()
        );
    }
}
