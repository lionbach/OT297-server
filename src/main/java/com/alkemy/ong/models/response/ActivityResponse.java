package com.alkemy.ong.models.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityResponse {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Timestamp timestamps;

}
