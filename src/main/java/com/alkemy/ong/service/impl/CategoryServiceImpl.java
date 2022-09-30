package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity =  categoryMapper.categoryRequest2CategoryEntity(categoryRequest);
        CategoryEntity categorySavedEntity = categoryRepository.save(categoryEntity);
        CategoryResponse categoryResponse = categoryMapper.categoryEntity2CategoryResponse(categorySavedEntity);
        return categoryResponse;
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Long id) {
        CategoryResponse categoryResponse;
        if (categoryRepository.existsById(id)){
            CategoryEntity categoryEntity =  categoryMapper.categoryRequest2CategoryUpdateEntity(categoryRequest, id);
            CategoryEntity categorySavedEntity = categoryRepository.save(categoryEntity);
            categoryResponse = categoryMapper.categoryEntity2CategoryResponse(categorySavedEntity);

        } else {
            categoryResponse = null;
        }
        return categoryResponse;
    }
}
