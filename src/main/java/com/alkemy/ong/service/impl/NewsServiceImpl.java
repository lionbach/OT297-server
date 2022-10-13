package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.entity.NewEntity;
import com.alkemy.ong.models.mapper.CommentMapper;
import com.alkemy.ong.models.mapper.NewsMapper;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.request.NewsUpdateRequest;
import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.AwsService;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    AuthService authService;
    @Autowired
    AwsService awsService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public NewsResponse save(NewsRequest news) throws IOException {
        NewEntity entity = newsMapper.newsRequest2NewEntity(news);
        NewEntity savedEntity = newsRepository.save(entity);
        NewsResponse response = newsMapper.newEntity2NewsResponse(savedEntity);
        return response;
    }


    @Override
    public ResponseEntity<NewsResponse> update(Long id, NewsUpdateRequest newsUpdateRequest, String token) throws IOException {

        ResponseEntity<NewsResponse> response;


        NewEntity entity = newsRepository.findById(id).orElseThrow();
        if (validInput(newsUpdateRequest.getName())) entity.setName(newsUpdateRequest.getName());
        if (validInput(newsUpdateRequest.getContent())) entity.setContent(newsUpdateRequest.getContent());
        if (validInput(newsUpdateRequest.getImage()))
            entity.setImage(awsService.uploadFileFromBase64(newsUpdateRequest.getImage()));// esto pasarlo al mapper
        if (newsUpdateRequest.getCategoryId() != null) entity.setCategoryId(newsUpdateRequest.getCategoryId());

        response = (ResponseEntity<NewsResponse>) ResponseEntity.status(HttpStatus.OK).body(newsMapper.newEntity2NewsResponse(newsRepository.save(entity)));
      /*  } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }*/
        return response;

    }

    @Override
    public ResponseEntity<NewsResponse> findById(Long id) throws IOException {
        ResponseEntity<NewsResponse> response;
        if (newsRepository.existsById(id)) {
            NewEntity slideEntity = newsRepository.findById(id).orElseThrow();
            response = ResponseEntity.status(HttpStatus.OK).body(newsMapper.newEntity2NewsResponse(slideEntity));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Override
    public ResponseEntity<Void> delete(Long id, String token) {
        ResponseEntity<Void> response;
        if (authService.roleValidator(id, token)) {
            newsRepository.deleteById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

    @Override
    public ResponseEntity<List<CommentResponse>> getAllCommentsById(Long id) {
        List<CommentEntity> commentEntityList = commentRepository.findAllByNewsId(id);
        List<CommentResponse> commentResponses = commentMapper.commentEntitiesList2CommentResponseList(commentEntityList);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponses);
    }

    public boolean validInput(String input) {
        return (input != null && !input.isEmpty() && !input.isBlank());
    }

}
