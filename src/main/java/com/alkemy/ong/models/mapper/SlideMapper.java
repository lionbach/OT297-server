package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.OrganizationResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

@Autowired
OrganizationMapper organizationMapper;

@Autowired
OrganizationRepository organizationRepository;
    public SlideEntity slideRequest2SlideEntity(SlideRequest slide) {

        SlideEntity entity = new SlideEntity();
        entity.setImageUrl(slide.getImageUrl());
        entity.setText(slide.getText());
     //   entity.setOrganization(organizationRepository.getById(slide.getOrganizationId()));
        entity.setOrganizationId(slide.getOrganizationId());
        return entity;
    }



    public SlideResponse slideEntity2SlideResponse(SlideEntity savedEntity) {
        SlideResponse response = new SlideResponse();
        response.setId(savedEntity.getId());
        response.setImageUrl(savedEntity.getImageUrl());
        response.setText(savedEntity.getText());
        response.setOrganization(savedEntity.getOrganization().getId());

        return response;
    }
}
