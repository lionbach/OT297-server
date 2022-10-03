package com.alkemy.ong.service;

import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.request.NewsUpdateRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.models.response.NewsUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface NewsService {

    public NewsResponse save(NewsRequest news) throws IOException;

    ResponseEntity<NewsResponse> update(Long id, NewsUpdateRequest newsUpdateRequest, String token) throws IOException;


    ResponseEntity<NewsResponse> findById(Long id) throws IOException;

    ResponseEntity<Void> delete(Long id, String token);
}
