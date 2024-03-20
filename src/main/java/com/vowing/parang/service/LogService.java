package com.vowing.parang.service;

import com.vowing.parang.dto.LogDTO;
import com.vowing.parang.entity.CategoryEntity;
import com.vowing.parang.entity.LogEntity;
import com.vowing.parang.error.CategoryNotFound;
import com.vowing.parang.repository.CategoryRepository;
import com.vowing.parang.repository.LogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<LogDTO> logList() {
        List<LogEntity> logEntityList = logRepository.findAll();

        return logEntityList.stream()
                .map(LogEntity::toValueObject)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<LogDTO> getLogByCategoryId(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFound::new);

        List<LogEntity> logEntityList = logRepository.findByCategoryId(categoryId);

        return logEntityList.stream()
                .map(LogEntity::toValueObject)
                .collect(Collectors.toList());
    }
/*
    @Transactional
    public Workbook generateLogListExcel(LocalDateTime fromDate, LocalDateTime toDate) {
        List<LogEntity> logEntityList = logRepository.findByTimestampBetween(fromDate, toDate);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Log List");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("생성일");
        headerRow.createCell(1).setCellValue("아이디");
        headerRow.createCell(2).setCellValue("구분");
        headerRow.createCell(3).setCellValue("기간");
        headerRow.createCell(4).setCellValue("개수");

        int rowNum = 1;
        for (LogEntity logEntity : logEntityList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(logEntity.getTimestamp()));
            row.createCell(1).setCellValue(logEntity.getUserId());
            row.createCell(2).setCellValue(logEntity.getStatus());
            row.createCell(3).setCellValue(logEntity.getWorkDay());
            row.createCell(4).setCellValue(logEntity.getAddNumber());
        }

        return workbook;
    }

     */

}
