package com.vowing.parang.entity;

import com.vowing.parang.dto.LogDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private String createLogTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
                this.getCreateLogTime(),
                this.getUserId(),
                this.getStatus(),
                this.getWorkDay(),
                this.getAddNumber(),
                this.getCategoryId()
        );
    }
}
