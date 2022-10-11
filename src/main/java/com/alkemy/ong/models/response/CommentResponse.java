package com.alkemy.ong.models.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private Long newsId;
    private String body;
    private Timestamp timestamps;
}
