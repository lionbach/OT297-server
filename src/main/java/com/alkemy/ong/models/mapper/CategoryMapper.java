package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    CategoryRepository categoryRepository;
    public CategoryEntity categoryRequest2CategoryEntity(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryRequest.getName());
        categoryEntity.setImage(categoryRequest.getImage());
        categoryEntity.setDescription(categoryRequest.getDescription());
        return categoryEntity;
    }

    public CategoryResponse categoryEntity2CategoryResponse(CategoryEntity categoryEntity) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(categoryEntity.getId());
        categoryResponse.setName(categoryEntity.getName());
        categoryResponse.setImage(categoryEntity.getImage());
        categoryResponse.setDescription(categoryEntity.getDescription());
        categoryResponse.setTimestamps(categoryEntity.getTimestamps());
        return categoryResponse;
    }

    public CategoryEntity categoryRequest2CategoryUpdateEntity(CategoryRequest categoryRequest, Long id) {

        CategoryEntity categoryEntity = categoryRequest2CategoryEntity(categoryRequest);
        categoryEntity.setId(id);
        categoryEntity.setTimestamps(categoryRepository.findById(id).get().getTimestamps());
        return categoryEntity;
    }
}
