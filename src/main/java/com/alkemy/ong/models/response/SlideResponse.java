package com.alkemy.ong.models.response;



import lombok.*;

import java.sql.Timestamp;


@Data
public class SlideResponse {
    private Long id;
    private String imageUrl;
    private String text;
    private Integer slideOrder;
    private OrganizationResponse organization;
    private Long organizationId;
    private Timestamp timestamps;
}
