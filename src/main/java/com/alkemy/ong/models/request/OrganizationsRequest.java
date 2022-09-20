package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationsRequest {
    private String name;
    private String image;
    private String address;
    private Integer phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
}
