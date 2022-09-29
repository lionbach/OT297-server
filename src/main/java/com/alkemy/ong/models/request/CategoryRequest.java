package com.alkemy.ong.models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class CategoryRequest {
    @NotNull
    private String name;
    private String description;
    private String image;
}
