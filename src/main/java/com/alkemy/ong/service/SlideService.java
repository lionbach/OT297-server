package com.alkemy.ong.service;

import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideResponse;

import java.io.IOException;

public interface SlideService {

    public SlideResponse save(SlideRequest slide) throws IOException;
}
