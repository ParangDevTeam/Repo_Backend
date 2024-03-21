package com.vowing.parang.domain.category.controller;

import com.vowing.parang.domain.category.dto.CategoryDTO;
import com.vowing.parang.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategory();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryDTOList);
    }
}
