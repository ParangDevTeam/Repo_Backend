package com.vowing.parang.controller;

import com.vowing.parang.dto.LogDTO;
import com.vowing.parang.service.LogService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/log/")
public class LogController {

    private final LogService logService;

    /**
     * 로그 목록
     * @return 로그 목록
     */
    @GetMapping("/")
    public ResponseEntity<List<LogDTO>> getAllLog() {
        List<LogDTO> logDTOList = logService.logList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logDTOList);
    }

    /**
     * 카테고리 별 목록
     * @param category category
     * @return 카테고리 별 목록
     */
    @GetMapping("/category")
    public ResponseEntity<List<LogDTO>> getCategorySelect(Long category) {
        List<LogDTO> LogDTOList = logService.getLogByCategoryId(category);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LogDTOList);
    }

    /**
     * 날짜별 엑셀 파일 다운로드
     * @param fromDate fromDate
     * @param toDate toDate
     * @return 날짜별 엑셀 파일 다운로드
     */
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportLogListExcel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDate
    ) {
        Workbook workbook = logService.generateLogListExcel(fromDate, toDate);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", fromDate.toString() + " ~ " + toDate.toString() + ".xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




}
