package com.alkemy.ong.service;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse save(CategoryRequest categoryRequest);
}
