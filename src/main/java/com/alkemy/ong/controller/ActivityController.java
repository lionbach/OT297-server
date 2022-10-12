package com.alkemy.ong.controller;




import com.alkemy.ong.models.request.ActivityRequest;
import com.alkemy.ong.models.response.ActivityResponse;
import com.alkemy.ong.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> save(@Valid @RequestBody ActivityRequest activityRequest) throws IOException {
        ActivityResponse activitySavedResponse  = activityService.save(activityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(activitySavedResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> update(@Valid @RequestBody ActivityRequest activityRequest, @PathVariable Long id ) throws IOException {
        ActivityResponse activityUpdatedResponse  = activityService.update(activityRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(activityUpdatedResponse);
    }
}
