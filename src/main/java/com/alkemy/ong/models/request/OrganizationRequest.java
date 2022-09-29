package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationRequest {
    private String name;
    private String image;
    private String address;
    private String phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
}
