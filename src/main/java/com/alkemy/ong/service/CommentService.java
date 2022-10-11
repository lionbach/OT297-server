package com.alkemy.ong.service;

import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.models.response.CommentResponse;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<CommentBasicResponse> listCommentsSortByTimestamps();

    CommentResponse save(CommentRequest commentRequest) throws IOException;
}
