package com.alkemy.ong.models.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPaginatedResponse {
    List<CategoryResponse> categoryPageContent;
    String nextPage;
    String previousPage;
}
