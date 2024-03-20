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

    @GetMapping("/")
    public ResponseEntity<List<LogDTO>> getAllLog() {
        List<LogDTO> logDTOList = logService.logList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logDTOList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<LogDTO>> getCategorySelect(Long category) {
        List<LogDTO> LogDTOList = logService.getLogByCategoryId(category);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LogDTOList);
    }
/*
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportLogListExcel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate
    ) {
        Workbook workbook = logService.generateLogListExcel(fromDate, toDate);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "log_list.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

 */
}
