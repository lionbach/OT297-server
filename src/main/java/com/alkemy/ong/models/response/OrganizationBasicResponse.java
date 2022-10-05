package com.alkemy.ong.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationBasicResponse {
    private Long id;
    private String name;
    private String image;
    private String address;
    private String phone;
}
