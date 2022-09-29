package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class SlideMapper {

@Autowired
OrganizationMapper organizationMapper;

@Autowired
OrganizationRepository organizationRepository;

@Autowired
AwsService awsService;

    public SlideEntity slideRequest2SlideEntity(SlideRequest slide) throws IOException {

        // get organization
        Optional<OrganizationEntity> OptionalOrganizationEntity = organizationRepository.findById(slide.getOrganizationId());

        //mapper
        SlideEntity entity = new SlideEntity();
        entity.setImageUrl(awsService.uploadFileFromBase64(slide.getImageUrl()));
        entity.setText(slide.getText());
        entity.setSliceOrder(slide.getSlideOrder());
        entity.setOrganization(OptionalOrganizationEntity.get());
        entity.setOrganizationId(slide.getOrganizationId());

        return entity;
    }



    public SlideResponse slideEntity2SlideResponse(SlideEntity savedEntity) {
        SlideResponse response = new SlideResponse();
        response.setId(savedEntity.getId());
        response.setImageUrl(savedEntity.getImageUrl());
        response.setText(savedEntity.getText());
        response.setSlideOrder(savedEntity.getSliceOrder());
        response.setOrganization(organizationMapper.organizationsEntity2OrganizationsResponse(savedEntity.getOrganization()));
        response.setOrganizationId(savedEntity.getOrganizationId());
        response.setTimestamps(savedEntity.getTimestamp());
        return response;
    }
}
