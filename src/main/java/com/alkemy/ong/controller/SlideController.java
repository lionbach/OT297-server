package com.alkemy.ong.controller;

import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @PostMapping
    public ResponseEntity<SlideResponse> create(@RequestBody @Valid SlideRequest slideRequest) throws IOException {
        SlideResponse saveResponse = slideService.save(slideRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveResponse);
    }

    @GetMapping
    public ResponseEntity<List<SlideBasicResponse>> getAll() {
        return ResponseEntity.ok(slideService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponse> getSlide(@RequestHeader(name = "Authorization") @PathVariable Long id) throws IOException {
        return slideService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlide(@RequestHeader(name = "Authorization") @PathVariable Long id) throws IOException {
        return slideService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponse> updateSlide(@Valid @PathVariable @RequestHeader(name = "Authorization") Long id, @RequestBody SlideRequest request) throws IOException {
        return slideService.update(id, request);
    }
}
