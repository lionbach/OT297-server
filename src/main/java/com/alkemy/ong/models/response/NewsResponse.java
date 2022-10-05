package com.alkemy.ong.models.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NewsResponse {
    private Long id;
    private String name;
    private String content;
    private String image;
    private CategoryResponse category;
    private Long categoryId;
    private Timestamp timestamps;
}
