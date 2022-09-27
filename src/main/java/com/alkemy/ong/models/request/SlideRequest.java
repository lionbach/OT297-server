package com.alkemy.ong.models.request;

import com.alkemy.ong.models.entity.OrganizationEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SlideRequest {

    private Long id;

    @NotNull
    @NotEmpty
    @NotEmpty
    private String imageUrl;

    @NotNull
    @NotEmpty
    @NotEmpty
    private String text;

    @NotNull
    private Long organizationId;
}
