package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.models.mapper.ActivityMapper;
import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityMapper activityMapper;
    @Override
    public ActivityResponse save(ActivityRequest activityRequest) throws IOException {
        ActivityEntity activityEntity =  activityMapper.activityRequest2ActivityEntity(activityRequest);
        ActivityEntity activitySavedEntity = activityRepository.save(activityEntity);
        ActivityResponse activityResponse = activityMapper.activityEntity2ActivityResponse(activitySavedEntity);
        return activityResponse;
    }

    @Override
    public ActivityResponse update(ActivityRequest activityRequest, Long id) throws IOException {
        if (!activityRepository.existsById(id)) throw new GenericException(HttpStatus.BAD_REQUEST, "id not exist", "The activity to update does not exist");
        ActivityEntity activityEntity =  activityMapper.activityRequest2ActivityUpdateEntity(activityRequest, id);
        ActivityEntity activitySavedEntity = activityRepository.save(activityEntity);
        ActivityResponse activityResponse = activityMapper.activityEntity2ActivityResponse(activitySavedEntity);
        return activityResponse;
    }
}
