package com.vowing.parang.domain.category.service;

import com.vowing.parang.domain.category.dto.CategoryDTO;
import com.vowing.parang.domain.category.entity.CategoryEntity;
import com.vowing.parang.domain.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 전체목록 가져오기
     */
    @Transactional
     public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList.stream()
                .map(CategoryEntity::toValueObject)
                .collect(Collectors.toList());
    }
}
