package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {
    @Autowired
    TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialResponse> create(@RequestBody @Valid TestimonialRequest request) throws IOException {
        TestimonialResponse response = testimonialService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialResponse> update(@RequestBody @Valid TestimonialRequest request, @PathVariable Long id) {
        TestimonialResponse response = testimonialService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        testimonialService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
