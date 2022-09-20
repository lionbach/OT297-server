package com.alkemy.ong.service;

import com.alkemy.ong.models.request.OrganizationsRequest;
import com.alkemy.ong.models.response.OrganizationsBasicResponse;
import com.alkemy.ong.models.response.OrganizationsResponse;

import java.util.List;

public interface OrganizationsService {
    OrganizationsResponse save(OrganizationsRequest organization);

    List<OrganizationsBasicResponse> getOrganizations();
}
