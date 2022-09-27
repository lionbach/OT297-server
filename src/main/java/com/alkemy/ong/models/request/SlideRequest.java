package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.response.OrganizationResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SlideRequest {

    @NotNull
    @NotEmpty
    @NotEmpty
    private String imageUrl;

    @NotNull
    @NotEmpty
    @NotEmpty
    private String text;

    private OrganizationResponse organization;

    @NotNull
    private Long organizationId;
}
