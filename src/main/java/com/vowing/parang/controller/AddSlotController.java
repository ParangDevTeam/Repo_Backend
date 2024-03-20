package com.vowing.parang.controller;

import com.vowing.parang.dto.AddSlotDTO;
import com.vowing.parang.service.AddSlotService;
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
@RequestMapping("/slotadd/")
public class AddSlotController {

    private final AddSlotService addSlotService;

    @PostMapping("save")
    public ResponseEntity<AddSlotDTO> slotSave(@RequestBody AddSlotDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addSlotService.addSlotSave(dto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AddSlotDTO> updateWorkDay (
            @PathVariable(name = "id") Long id,
            @RequestBody AddSlotDTO dto
    ) throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addSlotService.updateWorkDay(id,dto));
    }
}
