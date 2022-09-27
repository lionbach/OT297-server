package com.alkemy.ong.models.request;

import com.alkemy.ong.models.response.OrganizationResponse;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SlideRequest {

    @NotNull
    @NotEmpty
    private String imageUrl;

    @NotNull
    @NotEmpty
    private String text;

    private Integer slideOrder;

    private OrganizationResponse organization;

    @NotNull
    private Long organizationId;
}
