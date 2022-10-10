package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.mapper.CommentMapper;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentBasicResponse> listCommentsSortByTimestamps() {
        List<CommentEntity> entities = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamps"));
        List<CommentBasicResponse> responses = commentMapper.commentEntitiesList2CommentBasicResponse(entities);
        return responses;
    }
}
