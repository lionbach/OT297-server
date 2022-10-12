package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentBasicResponse>> getAll() {
        List<CommentBasicResponse> responses = commentService.listCommentsSortByTimestamps();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid CommentRequest commentRequest) throws IOException {
        CommentResponse response = commentService.save(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@Valid @PathVariable Long id, @RequestBody CommentRequest commentRequest, HttpServletRequest request) throws IOException {
        return commentService.update(id, commentRequest, request.getHeader("Authorization"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponse> delete(@Valid @PathVariable Long id, HttpServletRequest request) throws IOException {
        return commentService.delete(id, request.getHeader("Authorization"));
    }
}
