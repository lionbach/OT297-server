package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.mapper.CommentMapper;
import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AuthService authService;

    @Override
    public List<CommentBasicResponse> listCommentsSortByTimestamps() {
        List<CommentEntity> entities = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamps"));
        List<CommentBasicResponse> responses = commentMapper.commentEntitiesList2CommentBasicResponse(entities);
        return responses;
    }

    @Override
    public CommentResponse save(CommentRequest commentRequest) throws IOException {
        CommentEntity entity = commentMapper.commentRequest2CommentEntity(commentRequest);
        CommentEntity entitySaved = commentRepository.save(entity);
        CommentResponse response = commentMapper.commentEntity2CommentResponse(entitySaved);
        return response;
    }

    @Override
    public ResponseEntity<CommentResponse> update(Long id, CommentRequest request, String auth) {
        ResponseEntity<CommentResponse> response;
        Long userId = request.getUserId();
        Optional<CommentEntity> entity = commentRepository.findById(id);
        if (!entity.isEmpty()) {
            if (authService.roleValidator(userId, auth) && userId == entity.get().getUserId()) {
                entity.get().setBody(request.getBody());
                response = ResponseEntity.status(HttpStatus.OK).body(commentMapper.commentEntity2CommentResponse(commentRepository.save(entity.get())));
            } else {
                response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
