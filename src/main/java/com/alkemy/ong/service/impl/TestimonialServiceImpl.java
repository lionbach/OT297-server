package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.TestimonialEntity;
import com.alkemy.ong.models.mapper.TestimonialMapper;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {
    @Autowired
    TestimonialMapper testimonialMapper;
    @Autowired
    TestimonialRepository testimonialRepository;

    @Override
    public TestimonialResponse save(TestimonialRequest request) {
        TestimonialEntity entity = testimonialMapper.testimonialRequest2TestimonialEntity(request);
        TestimonialEntity entitySaved = testimonialRepository.save(entity);
        TestimonialResponse response = testimonialMapper.testimonialEntity2TestimonialResponse(entitySaved);
        return response;
    }

    @Override
    public TestimonialResponse update(TestimonialRequest request, Long id) {
            TestimonialEntity entity = testimonialRepository.findById(id).orElseThrow();
            TestimonialEntity entitySaved = testimonialRepository.save(entity);
            TestimonialResponse response = testimonialMapper.testimonialEntity2TestimonialResponse(entitySaved);
        return response;
    }
}
