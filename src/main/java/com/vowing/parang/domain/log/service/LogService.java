package com.vowing.parang.domain.log.service;

import com.vowing.parang.domain.log.dto.LogDTO;
import com.vowing.parang.domain.log.entity.LogEntity;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    /**
     * 로그 목록
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
     */
    @Transactional
    public Page<LogDTO> getFilteredLogsPaging(LocalDate fromDate, LocalDate toDate, String category, Pageable pageable) {
        Page<LogEntity> logEntityPage = logRepository.findByCreateLogTimeBetweenAndCategory(
                fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                category,
                 PageRequest.of(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),// 기본값이 10개
                            // 3, // 3개씩 출력
                            Sort.by(Sort.Direction.DESC, "id")
                    )
        );

        return logEntityPage.map(LogEntity::toValueObject);
    }

    /**
     * 날짜별 엑셀 파일 다운로드
     */
    @Transactional
    public Workbook LogListExcel(String fromDate, String toDate, String category) {
        List<LogEntity> logEntityList = logRepository.findAllByCreateLogTimeBetweenAndCategory(fromDate, toDate, category);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Log List");

        createHeader(sheet);

        int rowNum = 1;
        for (LogEntity logEntity : logEntityList) {
            Row row = sheet.createRow(rowNum++);
            createLogRow(row, logEntity);
        }

        return workbook;
    }

    /**
     *  엑셀 해더
     */
    private void createHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {"생성일", "아이디", "구분", "기간", "개수"};

        for(int i = 0; i < headers.length; i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }
    /**
     *  엑셀 내용
     */
    private void createLogRow(Row row, LogEntity logEntity) {
        row.createCell(0).setCellValue(String.valueOf(logEntity.getCreateLogTime()));
        row.createCell(1).setCellValue(logEntity.getUserId());
        row.createCell(2).setCellValue(logEntity.getStatus());
        row.createCell(3).setCellValue(logEntity.getWorkDay());
        row.createCell(4).setCellValue(logEntity.getAddNumber());
    }

}
