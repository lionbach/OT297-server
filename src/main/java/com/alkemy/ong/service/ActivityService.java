package com.alkemy.ong.service;

import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;

import java.io.IOException;

public interface ActivityService {

    ActivityResponse save(ActivityRequest activityRequest) throws IOException;

    ActivityResponse update(ActivityRequest activityRequest, Long id) throws IOException;
}
