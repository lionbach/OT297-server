package com.alkemy.ong.service;

import com.alkemy.ong.models.request.OrganizationRequest;
import com.alkemy.ong.models.response.OrganizationBasicResponse;
import com.alkemy.ong.models.response.OrganizationResponse;

import java.util.List;

public interface OrganizationService {
    OrganizationResponse save(OrganizationRequest organization);

    List<OrganizationBasicResponse> getOrganizations();
}
