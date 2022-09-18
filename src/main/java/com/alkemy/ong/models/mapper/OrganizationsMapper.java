package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.dto.OrganizationsBasicDTO;
import com.alkemy.ong.models.entity.OrganizationsEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationsMapper {
    public OrganizationsBasicDTO Entity2BasicDTO(OrganizationsEntity entity) {
        OrganizationsBasicDTO dto = new OrganizationsBasicDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
