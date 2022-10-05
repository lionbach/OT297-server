package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
