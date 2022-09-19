package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.OrganizationsEntity;
import com.alkemy.ong.models.mapper.OrganizationsMapper;
import com.alkemy.ong.models.request.OrganizationsRequest;
import com.alkemy.ong.models.response.OrganizationsBasicResponse;
import com.alkemy.ong.models.response.OrganizationsResponse;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {
    @Autowired
    private OrganizationsRepository organizationsRepository;
    @Autowired
    private OrganizationsMapper organizationsMapper;

    @Override
    public OrganizationsResponse save(OrganizationsRequest organization) {
        OrganizationsEntity entity = OrganizationsMapper.organizationsRequest2OrganizationsEntity(organization);
        OrganizationsEntity savedEntity = organizationsRepository.save(entity);
        OrganizationsResponse response = organizationsMapper.organizationsEntity2OrganizationsResponse(savedEntity);
        return response;
    }

    @Override
    public List<OrganizationsBasicResponse> getOrganizations() {
        List<OrganizationsEntity> entitylist = organizationsRepository.findAll();
        List<OrganizationsBasicResponse> responseList = new ArrayList<>();
        entitylist.forEach(ent -> responseList.add(
                organizationsMapper.organizationsEntity2OrganizationsBasicResponse(ent)
        ));
        return responseList;
    }
}
