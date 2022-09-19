package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.request.OrganizationsRequest;
import com.alkemy.ong.models.response.OrganizationsBasicResponse;
import com.alkemy.ong.models.entity.OrganizationsEntity;
import com.alkemy.ong.models.response.OrganizationsResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationsMapper {
    public static OrganizationsEntity organizationsRequest2OrganizationsEntity(OrganizationsRequest organization) {
        OrganizationsEntity entity = new OrganizationsEntity();
        entity.setName(organization.getName());
        entity.setImage(organization.getImage());
        entity.setAddress(organization.getAddress());
        entity.setPhone(organization.getPhone());
        entity.setEmail(organization.getEmail());
        entity.setWelcomeText(organization.getWelcomeText());
        entity.setAboutUsText(organization.getAboutUsText());
        return entity;
    }

    public OrganizationsBasicResponse organizationsEntity2OrganizationsBasicResponse(OrganizationsEntity entity) {
        OrganizationsBasicResponse response = new OrganizationsBasicResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setImage(entity.getImage());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());
        return response;
    }

    public OrganizationsResponse organizationsEntity2OrganizationsResponse(OrganizationsEntity savedEntity) {
        OrganizationsResponse response = new OrganizationsResponse();
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
