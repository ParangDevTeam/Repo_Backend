package com.vowing.parang.domain.log.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.log.dto.LogDTO;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.slot.dto.SlotDto;
import com.vowing.parang.domain.slot.dto.request.SlotCreateRequestDto;
import com.vowing.parang.domain.slot.entity.SlotEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    private String category;

    public LogDTO toValueObject() {
        return new LogDTO(
                this.getId(),
                this.getCreateLogTime(),
                this.getUserId(),
                this.getStatus(),
                this.getWorkDay(),
                this.getAddNumber(),
                this.getCategory()
        );
    }

    public static LogEntity createLogEntity(
            MemberEntity member, CategoryEntity category, SlotCreateRequestDto dto
    ) {
        final var logEntity = new LogEntity();
        logEntity.setCreateLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logEntity.setUserId(member.getUserId());
        logEntity.setStatus("신규");
        logEntity.setWorkDay(dto.getWorkDay());
        logEntity.setAddNumber(dto.getAddNumber());
        logEntity.setCategory(category.getCategory());
        return logEntity;
    }

    public static LogEntity createUpdateLogEntity(
            MemberEntity member, SlotEntity slotEntity, SlotDto dto
    ) {
        final var logEntity = new LogEntity();
        logEntity.setCreateLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logEntity.setUserId(member.getUserId());
        logEntity.setStatus("수정");
        logEntity.setWorkDay(dto.getWorkDay());
        logEntity.setAddNumber(dto.getAddNumber());
        logEntity.setCategory(slotEntity.getCategory().getCategory());
    return logEntity;
}




}
