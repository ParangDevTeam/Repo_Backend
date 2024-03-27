package com.vowing.parang.domain.slot.service;

import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.category.error.CategoryNotFound;
import com.vowing.parang.domain.category.repository.CategoryRepository;
import com.vowing.parang.domain.log.entity.LogEntity;
import com.vowing.parang.domain.log.repository.LogRepository;
import com.vowing.parang.domain.member.entity.MemberEntity;
import com.vowing.parang.domain.member.error.UserNotFound;
import com.vowing.parang.domain.member.repository.MemberRepository;
import com.vowing.parang.domain.slot.dto.SlotDto;
import com.vowing.parang.domain.slot.dto.request.SlotCreateRequestDto;
import com.vowing.parang.domain.slot.entity.SlotEntity;
import com.vowing.parang.domain.slot.repository.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final MemberRepository memberRepository;
    private final SlotRepository slotRepository;
    private final CategoryRepository categoryRepository;
    private final LogRepository logRepository;

    @Transactional
    public SlotDto addSlotSave(SlotCreateRequestDto dto) {
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(CategoryNotFound::new);

        MemberEntity member = memberRepository.findById(dto.getId())
                .orElseThrow(UserNotFound::new);

        SlotEntity addSlotEntity = new SlotEntity();
        addSlotEntity.setCategory(category);
        addSlotEntity.setUserId(member);
        addSlotEntity.setAddNumber(dto.getAddNumber());
        addSlotEntity.setWorkDay(dto.getWorkDay());
        addSlotEntity.setStartDate(dto.getStartDate());

        LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = startDate.plusDays(dto.getWorkDay() + 1);
        addSlotEntity.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));

        SlotEntity saveEntity = slotRepository.save(addSlotEntity);

        final var logEntity = new LogEntity();
        logEntity.setCreateLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logEntity.setUserId(member.getUserId());
        logEntity.setStatus("신규");
        logEntity.setWorkDay(dto.getWorkDay());
        logEntity.setAddNumber(dto.getAddNumber());
        logEntity.setCategory(category.getCategory());

        logRepository.save(logEntity);

        return saveEntity.toValueObject();
    }

    @Transactional
    public SlotDto updateWorkDay(Long id, SlotDto dto) throws Exception {
        final var entity = slotRepository.findById(id)
                .orElseThrow(UserNotFound::new);

        MemberEntity member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(UserNotFound::new);

        if(!entity.getUserId().equals(member)) {
            throw new Exception("ID가 일치하지 않습니다.");
        }
        entity.setWorkDay(dto.getWorkDay());

        LocalDate startDate = LocalDate.parse(entity.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = startDate.plusDays(dto.getWorkDay() + 1);
        entity.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));

        slotRepository.save(entity);

        final var logEntity = new LogEntity();
        logEntity.setCreateLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        logEntity.setUserId(dto.getUserId());
        logEntity.setStatus("수정");
        logEntity.setWorkDay(dto.getWorkDay());
        logEntity.setAddNumber(dto.getAddNumber());
        logEntity.setCategory(entity.getCategory().getCategory());

        logRepository.save(logEntity);

        return entity.toValueObject();
    }
}
