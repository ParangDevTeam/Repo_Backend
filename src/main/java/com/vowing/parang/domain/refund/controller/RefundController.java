package com.vowing.parang.domain.refund.controller;

import com.vowing.parang.domain.refund.dto.RefundDTO;
import com.vowing.parang.domain.refund.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/refund/")
public class RefundController {

    private final RefundService refundService;

    @PostMapping("/save")
    public ResponseEntity<RefundDTO> postRegisterRefund(@RequestBody RefundDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refundService.postRegisterRefund(dto));
    }
    @GetMapping("")
    public ResponseEntity<List<RefundDTO>> getRefundList() {
        List<RefundDTO> refundDTOList = refundService.refundList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refundDTOList);
    }

    @GetMapping("/filter/paging")
    public ResponseEntity<Page<RefundDTO>> getFilterRefundPaging(
            @RequestParam(name = "category") String category,
            @PageableDefault Pageable pageable
    ) {
        Page<RefundDTO> refundDTOPage = refundService.getFilterRefundPaging(category, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(refundDTOPage);
    }
}
