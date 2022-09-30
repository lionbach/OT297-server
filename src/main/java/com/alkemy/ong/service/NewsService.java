package com.alkemy.ong.service;

import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;

import java.io.IOException;

public interface NewsService {

    public NewsResponse save(NewsRequest news) throws IOException;
}
