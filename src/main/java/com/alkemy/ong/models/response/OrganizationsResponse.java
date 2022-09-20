package com.alkemy.ong.models.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrganizationsResponse {
    private Long id;
    private String name;
    private String image;
    private String address;
    private Integer phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private Timestamp timestamps;
}
