package com.alkemy.ong.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationsBasicResponse {
    private Long id;
    private String name;
    private String image;
    private String address;
    private Integer phone;
}
