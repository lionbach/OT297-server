package com.alkemy.ong.service;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialPageResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import javassist.NotFoundException;

public interface TestimonialService {
    TestimonialResponse save(TestimonialRequest request);

    TestimonialResponse update(TestimonialRequest request, Long id);

    void delete(Long id);

    TestimonialPageResponse pagination(Integer pageNumber) throws NotFoundException;
}
