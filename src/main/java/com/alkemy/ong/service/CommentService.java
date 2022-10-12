package com.alkemy.ong.service;

import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.models.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<CommentBasicResponse> listCommentsSortByTimestamps();

    CommentResponse save(CommentRequest commentRequest) throws IOException;

    ResponseEntity<CommentResponse> update(Long id, CommentRequest request, String auth);

    ResponseEntity<CommentResponse> delete(Long id, String auth);
}
