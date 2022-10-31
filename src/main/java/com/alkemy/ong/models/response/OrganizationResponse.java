package com.alkemy.ong.models.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class OrganizationResponse {
    private Long id;
    private String name;
    private String image;
    private String address;
    private String phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private String urlFacebook;
    private String urlInstagram;
    private String urlLinkedin;
    private Timestamp timestamps;
}
