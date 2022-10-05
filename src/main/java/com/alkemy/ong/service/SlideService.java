package com.alkemy.ong.service;

import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface SlideService {

    public SlideResponse save(SlideRequest slide);

    List<SlideBasicResponse> getAll();

    ResponseEntity<SlideResponse> findById(Long id) throws IOException;
}
