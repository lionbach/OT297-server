package com.alkemy.ong.service;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;

public interface TestimonialService {
    TestimonialResponse save(TestimonialRequest request);

    TestimonialResponse update(TestimonialRequest request, Long id);
}
