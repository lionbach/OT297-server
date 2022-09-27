package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.mapper.OrganizationMapper;
import com.alkemy.ong.models.mapper.SlideMapper;
import com.alkemy.ong.models.request.OrganizationRequest;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.OrganizationResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;
    @Override
    public SlideResponse save(SlideRequest slide) {
        SlideEntity entity = slideMapper.slideRequest2SlideEntity(slide);
        SlideEntity savedEntity = slideRepository.save(entity);
        SlideResponse response = slideMapper.slideEntity2SlideResponse(savedEntity);
        return response;
    }



}
