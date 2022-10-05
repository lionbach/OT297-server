package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.mapper.CategoryMapper;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryOnlyNameResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) throws IOException {
        CategoryEntity categoryEntity =  categoryMapper.categoryRequest2CategoryEntity(categoryRequest);
        CategoryEntity categorySavedEntity = categoryRepository.save(categoryEntity);
        CategoryResponse categoryResponse = categoryMapper.categoryEntity2CategoryResponse(categorySavedEntity);
        return categoryResponse;
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Long id) throws IOException {
        if (!categoryRepository.existsById(id)) throw new GenericException(HttpStatus.BAD_REQUEST, "id not exist", "The category to update does not exist");
        CategoryEntity categoryEntity =  categoryMapper.categoryRequest2CategoryUpdateEntity(categoryRequest, id);
        CategoryEntity categorySavedEntity = categoryRepository.save(categoryEntity);
        CategoryResponse categoryResponse = categoryMapper.categoryEntity2CategoryResponse(categorySavedEntity);
        return categoryResponse;
    }

    @Override
    public List<CategoryOnlyNameResponse> listCategories() {
        List<CategoryEntity> categoryEntity =  categoryRepository.findAll();
        List<CategoryOnlyNameResponse> categoriesResponse = categoryMapper.listCategoryEntity2ListCategoryOnlyNameResponse(categoryEntity);
        return categoriesResponse;
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        if (!categoryRepository.existsById(id)) throw new GenericException(HttpStatus.NOT_FOUND, "id not exist", "The category does not exist");
        CategoryEntity entity = categoryRepository.getById(id);
        CategoryResponse categoryResponse = categoryMapper.categoryEntity2CategoryResponse(entity);
        return categoryResponse;
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) throw new GenericException(HttpStatus.NOT_FOUND, "id not exist", "The category to delete does not exist");
        categoryRepository.deleteById(id);
    }
}
