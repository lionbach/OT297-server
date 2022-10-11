package com.alkemy.ong.models.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestimonialResponse {
    private Long id;
    private String name;
    private String image;
    private String content;
    private Timestamp timestamps;
}
