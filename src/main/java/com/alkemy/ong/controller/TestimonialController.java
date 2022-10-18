package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialPageResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BoundPropertiesTrackingBindHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/testimonials")
@Api(tags = "Testimonials", description = "CRUD methods pertaining to Testimonials")
public class TestimonialController {
    @Autowired
    TestimonialService testimonialService;

    @PostMapping
    @ApiOperation(value = "Create a Testimonial", notes = "Create and return a Testimonial")
    @ApiResponse(code = 200, message = "CREATED")
    public ResponseEntity<TestimonialResponse> create(@RequestBody @Valid @ApiParam
            (name = "Create a Testimonial", value = "TestimonialRequest", required = true) TestimonialRequest request) throws IOException {
        TestimonialResponse response = testimonialService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a testimonial by id",
            notes = "Update and return a testimonial according to the id" + ", replacing the one that was in that ID")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Testimonial not found")})
    public ResponseEntity<TestimonialResponse> update(@RequestBody @Valid @ApiParam
            (name = "Update a Testimonial", value = "Testimonial Request") TestimonialRequest request, @PathVariable
                                                      @ApiParam(name = "id", type = "Long",
                                                              value = "Testimonial Id", example = "1",
                                                              required = true) Long id) {
        TestimonialResponse response = testimonialService.update(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Testimonial by id", notes = "Delete a testimonial according to the id")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Testimonial not found")})
    public ResponseEntity<Void> delete(@PathVariable
                                       @ApiParam(name = "id", type = "Long", value = "Testimonial id", example = "1",
                                               required = true) Long id) {
        testimonialService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Get all testimonials in pages of 10", notes = "Returns 10 testimonials per page")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 403, message = "ERROR")})
    public ResponseEntity<TestimonialPageResponse> getAllPage(@RequestParam(defaultValue = "1") Integer page) throws NotFoundException {
        return ResponseEntity.ok(testimonialService.pagination(page));
    }
}
