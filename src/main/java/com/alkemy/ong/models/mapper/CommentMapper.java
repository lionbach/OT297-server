package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentBasicResponse;
import com.alkemy.ong.models.response.CommentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    public List<CommentBasicResponse> commentEntitiesList2CommentBasicResponseList(List<CommentEntity> entities) {
        List<CommentBasicResponse> responses = new ArrayList<>();
        for (CommentEntity entity : entities) {
            responses.add(commentEntity2CommentBasicResponse(entity));
        }
        return responses;
    }

    private CommentBasicResponse commentEntity2CommentBasicResponse(CommentEntity entity) {
        CommentBasicResponse response = new CommentBasicResponse();
        response.setBody(entity.getBody());
        return response;
    }

    public CommentEntity commentRequest2CommentEntity(CommentRequest commentRequest) {
        CommentEntity entity = new CommentEntity();
        entity.setBody(commentRequest.getBody());
        entity.setNewsId(commentRequest.getNewsId());
        entity.setUserId(commentRequest.getUserId());
        return entity;
    }

    public CommentResponse commentEntity2CommentResponse(CommentEntity entity) {
        CommentResponse response = new CommentResponse();
        response.setId(entity.getId());
        response.setNewsId(entity.getNewsId());
        response.setUserId(entity.getUserId());
        response.setBody(entity.getBody());
        response.setTimestamps(entity.getTimestamps());
        return response;
    }

    public List<CommentResponse> commentEntitiesList2CommentResponseList(List<CommentEntity> entities) {
        List<CommentResponse> responses = new ArrayList<>();
        for (CommentEntity entity : entities) {
            responses.add(commentEntity2CommentResponse(entity));
        }
        return responses;
    }
}
