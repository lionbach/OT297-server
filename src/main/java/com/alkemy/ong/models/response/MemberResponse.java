package com.alkemy.ong.models.response;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class MemberResponse {
    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private Timestamp timestamps;
}
