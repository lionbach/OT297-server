package com.alkemy.ong.models.response;


import com.alkemy.ong.models.entity.OrganizationEntity;
import lombok.*;


import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Data
public class SlideResponse {
    private Long id;
    private String imageUrl;
    private String text;
    private OrganizationResponse organization;
    private Long organizationId;
    private Timestamp timestamps;
}
