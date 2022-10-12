package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.ActivityEntity;
import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ActivityMapper {
    @Autowired
    AwsService awsService;

    @Autowired
    ActivityRepository activityRepository;

    public ActivityEntity activityRequest2ActivityEntity(ActivityRequest activityRequest) throws IOException {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setName(activityRequest.getName());
        activityEntity.setContent(activityRequest.getContent());
        activityEntity.setImage(awsService.uploadFileFromBase64(activityRequest.getImage()));
        return activityEntity;

    }

    public ActivityResponse activityEntity2ActivityResponse(ActivityEntity activitySavedEntity) {

        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activitySavedEntity.getId());
        activityResponse.setName(activitySavedEntity.getName());
        activityResponse.setContent(activitySavedEntity.getContent());
        activityResponse.setImage(activitySavedEntity.getImage());
        activityResponse.setTimestamps(activitySavedEntity.getTimestamps());

        return activityResponse;
    }

    public ActivityEntity activityRequest2ActivityUpdateEntity(ActivityRequest activityRequest, Long id) throws IOException {

        ActivityEntity activityEntity = activityRequest2ActivityEntity(activityRequest);
        activityEntity.setId(id);
        activityEntity.setTimestamps(activityRepository.findById(id).get().getTimestamps());

        return activityEntity;

    }
}
