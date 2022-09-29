package com.alkemy.ong.models.response;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Timestamp timestamps;
}
