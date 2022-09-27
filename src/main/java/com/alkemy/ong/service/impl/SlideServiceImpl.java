package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.mapper.SlideMapper;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;
    @Override
    public SlideResponse save(SlideRequest slide) {
        SlideEntity entity = slideMapper.slideRequest2SlideEntity(slide);
        //verify order
        if (entity.getSliceOrder() == null || entity.getSliceOrder() == 0 ){
            List<SlideEntity> listEntitys= slideRepository.findAll(Sort.by(Sort.Direction.DESC, "sliceOrder"));
            entity.setSliceOrder(listEntitys.get(0).getSliceOrder()+1);
        }
        SlideEntity savedEntity = slideRepository.save(entity);
        SlideResponse response = slideMapper.slideEntity2SlideResponse(savedEntity);
        return response;
    }



}
