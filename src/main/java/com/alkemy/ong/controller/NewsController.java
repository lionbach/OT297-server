package com.alkemy.ong.controller;


import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid NewsRequest newsRequest) throws IOException {
        NewsResponse saveResponse = newsService.save(newsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveResponse);
    }
}
