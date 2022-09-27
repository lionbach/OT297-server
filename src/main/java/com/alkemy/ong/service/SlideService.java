package com.alkemy.ong.service;

import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideResponse;

public interface SlideService {

    public SlideResponse save(SlideRequest slide);
}
