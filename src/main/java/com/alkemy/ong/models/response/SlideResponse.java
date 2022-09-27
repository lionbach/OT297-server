package com.alkemy.ong.models.response;


import com.alkemy.ong.models.entity.OrganizationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SlideResponse {

    private Long id;

    private String imageUrl;

    private String text;

    private Timestamp timestamps;

    private Long organization;
}
