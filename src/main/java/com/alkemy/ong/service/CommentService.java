package com.alkemy.ong.service;

import com.alkemy.ong.models.response.CommentBasicResponse;

import java.util.List;

public interface CommentService {
    List<CommentBasicResponse> listCommentsSortByTimestamps();
}
