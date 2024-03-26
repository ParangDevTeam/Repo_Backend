package com.vowing.parang.domain.log.service;

import com.vowing.parang.domain.log.dto.LogDTO;
import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.log.entity.LogEntity;
import com.vowing.parang.domain.category.error.CategoryNotFound;
import com.vowing.parang.domain.category.repository.CategoryRepository;
import com.vowing.parang.domain.log.repository.LogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 로그 목록
     * @return 로그 목록
     */
    @Transactional
    public List<LogDTO> logList() {
        List<LogEntity> logEntityList = logRepository.findAll();

        return logEntityList.stream()
                .map(LogEntity::toValueObject)
                .collect(Collectors.toList());
    }

     /**
     * 카테고리 별 페이징 목록
     * @param fromDate 시작 날짜
     * @param toDate 끝 날짜
     * @param category 카테고리
     * @param pageable 페이징 처리
     * @return 카테고리 별 페이징 목록
     */
    @Transactional
    public Page<LogDTO> getFilteredLogsPaging(LocalDate fromDate, LocalDate toDate, String category, Pageable pageable) {
        Page<LogEntity> logEntityPage = logRepository.findByCreateLogTimeBetweenAndCategory(
                fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                category,
                 PageRequest.of(
                            pageable.getPageNumber(),
                            // pageable.getPageSize(),// 기본값이 10개
                            3,
                            Sort.by(Sort.Direction.DESC, "id")
                    )
        );

        return logEntityPage.map(LogEntity::toValueObject);
    }

    /**
     * 날짜별 엑셀 파일 다운로드
     * @param fromDate fromDate
     * @param toDate toDate
     * @return 날짜 별 엑셀 파일
     */

    @Transactional
    public Workbook LogListExcel(String fromDate, String toDate, String category) {
        List<LogEntity> logEntityList = logRepository.findAllByCreateLogTimeBetweenAndCategory(fromDate, toDate, category);

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
            row.createCell(0).setCellValue(String.valueOf(logEntity.getCreateLogTime()));
            row.createCell(1).setCellValue(logEntity.getUserId());
            row.createCell(2).setCellValue(logEntity.getStatus());
            row.createCell(3).setCellValue(logEntity.getWorkDay());
            row.createCell(4).setCellValue(logEntity.getAddNumber());
        }

        return workbook;
    }





}
