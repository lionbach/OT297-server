package com.alkemy.ong.service;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryOnlyNameResponse;
import com.alkemy.ong.models.response.CategoryPaginatedResponse;
import com.alkemy.ong.models.response.CategoryResponse;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryResponse save(CategoryRequest categoryRequest) throws IOException;
    CategoryResponse update(CategoryRequest categoryRequest, Long id) throws IOException;
    List<CategoryOnlyNameResponse> listCategories();
    CategoryResponse getCategory(Long id);
    void deleteCategory(Long id);

    CategoryPaginatedResponse findAllPaginated(Integer numberOfPage, Integer quantityOfResults);
}
