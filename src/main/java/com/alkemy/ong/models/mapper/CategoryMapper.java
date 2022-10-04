package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryOnlyNameResponse;
import com.alkemy.ong.models.response.CategoryResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    @Autowired
    AwsService awsService;
    @Autowired
    CategoryRepository categoryRepository;
    public CategoryEntity categoryRequest2CategoryEntity(CategoryRequest categoryRequest) throws IOException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryRequest.getName());
        categoryEntity.setImage(awsService.uploadFileFromBase64(categoryRequest.getImage()));
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

    public CategoryEntity categoryRequest2CategoryUpdateEntity(CategoryRequest categoryRequest, Long id) throws IOException {

        CategoryEntity categoryEntity = categoryRequest2CategoryEntity(categoryRequest);
        categoryEntity.setId(id);
        categoryEntity.setTimestamps(categoryRepository.findById(id).get().getTimestamps());
        return categoryEntity;
    }

    public List<CategoryOnlyNameResponse> listCategoryEntity2ListCategoryOnlyNameResponse(List<CategoryEntity> categoryEntity) {
        List<CategoryOnlyNameResponse> listResponse = new ArrayList<>();
        for (CategoryEntity ent : categoryEntity) {
            CategoryOnlyNameResponse res = new CategoryOnlyNameResponse();
            res.setName(ent.getName());
            listResponse.add(res);
        }
        return listResponse;
    }
}
