package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.mapper.SlideMapper;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    SlideMapper slideMapper;

    @Override
    public SlideResponse save(SlideRequest slide) throws IOException {
        SlideEntity entity = slideMapper.slideRequest2SlideEntity(slide);
        //verify order
        if (entity.getSlideOrder() == null || entity.getSlideOrder() == 0) {
            List<SlideEntity> listEntitys = slideRepository.findAll(Sort.by(Sort.Direction.DESC, "sliceOrder"));
            entity.setSlideOrder(listEntitys.get(0).getSlideOrder() + 1);
        }
        SlideEntity savedEntity = slideRepository.save(entity);
        SlideResponse response = slideMapper.slideEntity2SlideResponse(savedEntity);
        return response;
    }

    @Override
    public List<SlideBasicResponse> getAll() {
        List<SlideEntity> slideEntities = slideRepository.findAll();
        return slideMapper.slideEntitiesList2SlideBasicResponseList(slideEntities);
    }

    @Override
    public ResponseEntity<SlideResponse> findById(Long id) throws IOException {
        ResponseEntity<SlideResponse> response;
        if (slideRepository.existsById(id)) {
            SlideEntity slideEntity = slideRepository.findById(id).orElseThrow();
            response = ResponseEntity.status(HttpStatus.OK).body(slideMapper.slideEntity2SlideResponse(slideEntity));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
