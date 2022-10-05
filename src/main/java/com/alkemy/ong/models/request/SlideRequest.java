package com.alkemy.ong.models.request;

import com.alkemy.ong.models.response.OrganizationResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SlideRequest {

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String imageUrl;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String text;

    private Integer slideOrder;

    private OrganizationResponse organization;

    @NotNull(message = "cannot be null")
    private Long organizationId;
}
