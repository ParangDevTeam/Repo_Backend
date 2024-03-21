package com.vowing.parang.service;

import com.vowing.parang.dto.CategoryDTO;
import com.vowing.parang.entity.CategoryEntity;
import com.vowing.parang.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
     public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList.stream()
                .map(CategoryEntity::toValueObject)
                .collect(Collectors.toList());
    }
}
