package com.alkemy.ong.models.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ContactResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String message;
    private Timestamp timestamps;
}
