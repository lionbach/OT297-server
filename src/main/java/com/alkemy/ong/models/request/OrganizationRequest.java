package com.alkemy.ong.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrganizationRequest {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String name;

    private String image;
    private String address;
    private String phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
}
