package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryEntity categoryRequest2CategoryEntity(CategoryRequest request) {
        CategoryEntity response = new CategoryEntity();
        response.setName(request.getName());
        response.setImage(request.getImage());
        response.setDescription(request.getDescription());
        return response;
    }

    public CategoryResponse categoryEntity2CategoryResponse(CategoryEntity request) {
        CategoryResponse response = new CategoryResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setImage(request.getImage());
        response.setDescription(request.getDescription());
        response.setTimestamps(request.getTimestamps());
        return response;
    }

}
