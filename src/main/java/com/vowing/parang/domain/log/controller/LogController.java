package com.vowing.parang.domain.log.controller;

import com.vowing.parang.domain.log.dto.LogDTO;
import com.vowing.parang.domain.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/log/")
public class LogController {

    private final LogService logService;

    /**
     * 로그 목록
     */
    @GetMapping("/")
    public ResponseEntity<List<LogDTO>> getAllLog() {
        List<LogDTO> logDTOList = logService.logList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logDTOList);
    }

    /**
     * 카테고리 별 페이징 목록
     */
    @GetMapping("/filter/paging")
    public ResponseEntity<Page<LogDTO>> getFilteredLogsPaging(
        @RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
        @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
        @RequestParam(name = "category") String category,
        @PageableDefault Pageable pageable
    ) {
        Page<LogDTO> filteredLogsPage = logService.getFilteredLogsPaging(fromDate, toDate, category, pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredLogsPage);
    }

    /**
     * 날짜별 엑셀 파일 다운로드
     */
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportLogListExcel (
        @RequestParam(name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String fromDate,
        @RequestParam(name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String toDate,
        @RequestParam(name = "category") String category
    ) throws IOException {
        Workbook workbook = logService.LogListExcel(fromDate, toDate, category);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", fromDate.toString() + " ~ " + toDate.toString() + ".xlsx");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(outputStream.toByteArray());
    }



}
