package com.alkemy.ong.models.request;

import com.alkemy.ong.models.response.CategoryResponse;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewsRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private String image;

    private CategoryResponse category;

    //@NotNull
    private Long categoryId;
}
