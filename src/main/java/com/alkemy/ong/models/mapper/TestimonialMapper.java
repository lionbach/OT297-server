package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.TestimonialEntity;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialPageResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestimonialMapper {
    public TestimonialEntity testimonialRequest2TestimonialEntity(TestimonialRequest request) {
        TestimonialEntity entity = new TestimonialEntity();
        entity.setName(request.getName());
        entity.setImage(request.getImage());
        entity.setContent(request.getContent());
        return entity;
    }

    public TestimonialResponse testimonialEntity2TestimonialResponse(TestimonialEntity entity) {
        TestimonialResponse response = new TestimonialResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setImage(entity.getImage());
        response.setContent(entity.getContent());
        response.setTimestamps(entity.getTimestamp());
        return response;
    }

    public TestimonialPageResponse testimonialEntityList2TestimonialPageResponse(List<TestimonialEntity> testimonialEntityList, String next, String previous) {
        return new TestimonialPageResponse(testimonialEntityList2TestimonialResponseList(testimonialEntityList), previous, next);
    }

    private List<TestimonialResponse> testimonialEntityList2TestimonialResponseList(List<TestimonialEntity> testimonialEntityList) {
        return testimonialEntityList.stream()
                .map(this::testimonialEntity2TestimonialResponse)
                .collect(Collectors.toList());
    }
}
