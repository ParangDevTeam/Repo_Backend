package com.vowing.parang.service;

import com.vowing.parang.dto.AddSlotDTO;
import com.vowing.parang.entity.AddSlotEntity;
import com.vowing.parang.entity.CategoryEntity;
import com.vowing.parang.entity.LogEntity;
import com.vowing.parang.entity.MemberEntity;
import com.vowing.parang.error.CategoryNotFound;
import com.vowing.parang.error.UserNotFound;
import com.vowing.parang.repository.AddSlotRepository;
import com.vowing.parang.repository.CategoryRepository;
import com.vowing.parang.repository.LogRepository;
import com.vowing.parang.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AddSlotService {

    private final MemberRepository memberRepository;
    private final AddSlotRepository addSlotRepository;
    private final CategoryRepository categoryRepository;
    private final LogRepository logRepository;

    @Transactional
    public AddSlotDTO addSlotSave(AddSlotDTO dto) {
        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(CategoryNotFound::new);

        MemberEntity member = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(UserNotFound::new);

        AddSlotEntity addSlotEntity = new AddSlotEntity();
        addSlotEntity.setCategory(category);
        addSlotEntity.setUserId(member);
        addSlotEntity.setAddNumber(dto.getAddNumber());
        addSlotEntity.setWorkDay(dto.getWorkDay());
        addSlotEntity.setStartDate(dto.getStartDate());

        LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = startDate.plusDays(dto.getWorkDay() + 1);
        addSlotEntity.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));

        AddSlotEntity saveEntity = addSlotRepository.save(addSlotEntity);

        LogEntity logEntity = LogEntity.builder()
                .createLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .userId(dto.getUserId())
                .status("신규")
                .workDay(dto.getWorkDay())
                .addNumber(dto.getAddNumber())
                .categoryId(dto.getCategoryId())
                .build();

        logRepository.save(logEntity);

        return saveEntity.toValueObject();
    }

    @Transactional
    public AddSlotDTO updateWorkDay(Long id, AddSlotDTO dto) throws Exception {
        final var entity = addSlotRepository.findById(id)
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

            addSlotRepository.save(entity);

            LogEntity logEntity = LogEntity.builder()
                    .createLogTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .userId(dto.getUserId())
                    .status("수정")
                    .workDay(dto.getWorkDay())
                    .addNumber(entity.getAddNumber())
                    .categoryId(entity.getCategory().getId())
                    .build();

            logRepository.save(logEntity);

        return entity.toValueObject();
    }
}
