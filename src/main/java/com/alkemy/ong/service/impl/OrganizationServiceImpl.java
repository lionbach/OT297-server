package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.mapper.OrganizationMapper;
import com.alkemy.ong.models.request.OrganizationRequest;
import com.alkemy.ong.models.response.OrganizationBasicResponse;
import com.alkemy.ong.models.response.OrganizationResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public OrganizationResponse save(OrganizationRequest organization) {
        OrganizationEntity entity = organizationMapper.organizationsRequest2OrganizationsEntity(organization);
        OrganizationEntity savedEntity = organizationRepository.save(entity);
        OrganizationResponse response = organizationMapper.organizationsEntity2OrganizationsResponse(savedEntity);
        return response;
    }

    @Override
    public List<OrganizationBasicResponse> getOrganizations() {
        List<OrganizationEntity> entitylist = organizationRepository.findAll();
        List<OrganizationBasicResponse> responseList = new ArrayList<>();
        entitylist.forEach(ent -> responseList.add(
                organizationMapper.organizationsEntity2OrganizationsBasicResponse(ent)
        ));
        return responseList;
    }
}
