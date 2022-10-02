package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.entity.NewEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.models.response.NewsUpdateResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class NewsMapper {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AwsService awsService;
    public NewEntity newsRequest2NewEntity(NewsRequest news) throws IOException {

        // get category
        Optional<CategoryEntity> OptionalCategoryEntity = categoryRepository.findById(news.getCategoryId());

        //mapper
        NewEntity entity = new NewEntity();
        entity.setName(news.getName());
        entity.setContent(news.getContent());
        entity.setImage(awsService.uploadFileFromBase64(news.getImage()));
        entity.setCategory(OptionalCategoryEntity.get());
        entity.setCategoryId(news.getCategoryId());

        return entity;
    }

    public NewsResponse newEntity2NewsResponse(NewEntity savedEntity) {
        NewsResponse response = new NewsResponse();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());
        response.setImage(savedEntity.getImage());
        response.setContent(savedEntity.getContent());
        response.setCategory(categoryMapper.categoryEntity2CategoryResponse(savedEntity.getCategory()));
        response.setCategoryId(savedEntity.getCategoryId());
        response.setTimestamps(savedEntity.getTimestamp());
        return response;
    }

    public NewsUpdateResponse newsEntity2NewsUpdateResponse(NewEntity newsEntity) {
        NewsUpdateResponse newsUpdateResponse = new NewsUpdateResponse();
        newsUpdateResponse.setName(newsEntity.getName());
        newsUpdateResponse.setContent(newsEntity.getContent());
        newsUpdateResponse.setImage(newsUpdateResponse.getImage());
        newsUpdateResponse.setCategoryId(newsUpdateResponse.getCategoryId());

        return newsUpdateResponse;
    }

}
