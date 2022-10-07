package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.request.OrganizationRequest;
import com.alkemy.ong.models.response.OrganizationBasicResponse;
import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.response.OrganizationResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public OrganizationEntity organizationsRequest2OrganizationsEntity(OrganizationRequest organization) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(organization.getName());
        entity.setImage(organization.getImage());
        entity.setAddress(organization.getAddress());
        entity.setPhone(organization.getPhone());
        entity.setEmail(organization.getEmail());
        entity.setWelcomeText(organization.getWelcomeText());
        entity.setAboutUsText(organization.getAboutUsText());
        return entity;
    }

    public OrganizationBasicResponse organizationsEntity2OrganizationsBasicResponse(OrganizationEntity entity) {
        OrganizationBasicResponse response = new OrganizationBasicResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setImage(entity.getImage());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());
        return response;
    }

    public OrganizationResponse organizationsEntity2OrganizationsResponse(OrganizationEntity savedEntity) {
        OrganizationResponse response = new OrganizationResponse();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());
        response.setImage(savedEntity.getImage());
        response.setAddress(savedEntity.getAddress());
        response.setPhone(savedEntity.getPhone());
        response.setEmail(savedEntity.getEmail());
        response.setWelcomeText(savedEntity.getWelcomeText());
        response.setAboutUsText(savedEntity.getAboutUsText());
        response.setTimestamps(savedEntity.getTimestamps());
        return response;
    }
}
