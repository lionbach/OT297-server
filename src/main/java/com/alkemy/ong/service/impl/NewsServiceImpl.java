package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.NewEntity;
import com.alkemy.ong.models.mapper.NewsMapper;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public NewsResponse save(NewsRequest news) throws IOException {
        NewEntity entity = newsMapper.newsRequest2NewEntity(news);
        NewEntity savedEntity = newsRepository.save(entity);
        NewsResponse response = newsMapper.newEntity2NewsResponse(savedEntity);
        return response;
    }
}
