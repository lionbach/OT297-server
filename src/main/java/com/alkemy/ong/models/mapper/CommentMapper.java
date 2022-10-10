package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.response.CommentBasicResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    public List<CommentBasicResponse> commentEntitiesList2CommentBasicResponse(List<CommentEntity> entities) {
        List<CommentBasicResponse> responses = new ArrayList<>();
        for (CommentEntity entity : entities) {
            responses.add(commentEntity2CommentResponse(entity));
        }
        return responses;
    }

    private CommentBasicResponse commentEntity2CommentResponse(CommentEntity entity) {
        CommentBasicResponse response = new CommentBasicResponse();
        response.setBody(entity.getBody());
        return response;
    }
}
