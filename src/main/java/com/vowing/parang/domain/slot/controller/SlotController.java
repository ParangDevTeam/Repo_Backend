package com.vowing.parang.domain.slot.controller;

import com.vowing.parang.domain.slot.dto.SlotDto;
import com.vowing.parang.domain.slot.dto.request.SlotCreateRequestDto;
import com.vowing.parang.domain.slot.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/slotadd")
public class SlotController {

    private final SlotService slotService;

    @PostMapping("/save")
    public ResponseEntity<SlotDto> slotSave(@RequestBody SlotCreateRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(slotService.addSlotSave(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SlotDto> updateWorkDay (
            @PathVariable(name = "id") Long id,
            @RequestBody SlotDto dto
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(slotService.updateWorkDay(id,dto));
    }
}
