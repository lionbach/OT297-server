package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.dto.OrganizationsBasicDTO;
import com.alkemy.ong.models.entity.OrganizationsEntity;
import com.alkemy.ong.models.mapper.OrganizationsMapper;
import com.alkemy.ong.repository.OrganizationsRepository;
import com.alkemy.ong.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {
    @Autowired
    private OrganizationsRepository organizationsRepository;
    @Autowired
    private OrganizationsMapper organizationsMapper;
    @Override
    public OrganizationsBasicDTO getOne() {
        List<OrganizationsEntity> entitylist = organizationsRepository.findAll();
        OrganizationsEntity entity = entitylist.get(0);
        OrganizationsBasicDTO dto =   organizationsMapper.Entity2BasicDTO(entity);

        //OrganizationsBasicDTO dto = new OrganizationsBasicDTO();
        //dto.setName("Lion");

        return dto;
    }
}
