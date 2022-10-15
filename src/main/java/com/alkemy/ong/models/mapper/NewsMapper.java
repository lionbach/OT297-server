package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.entity.NewEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.*;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.AwsService;
import com.alkemy.ong.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public NewsResponse newEntity2NewsResponse(NewEntity savedEntity) throws IOException {
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

    public NewsPaginatedResponse paginationUtils2NewsPaginationResponse(PaginationUtils pagination) throws IOException {
        Page page = pagination.getPage();
        List<NewEntity> newsEntityList =  page.getContent();
        List<NewsResponse> newsResponseList = newsEntityList2NewsResponseList(newsEntityList);

        NewsPaginatedResponse newsPaginatedResponse = new NewsPaginatedResponse();
        newsPaginatedResponse.setNewsPageContent(newsResponseList);
        newsPaginatedResponse.setPreviousPage(pagination.getPrevious());
        newsPaginatedResponse.setNextPage(pagination.getNext());
        return newsPaginatedResponse;
    }

    public List<NewsResponse> newsEntityList2NewsResponseList(List<NewEntity> newsEntityList) throws IOException {
        List<NewsResponse> mapperResponse = new ArrayList<>();
        for (NewEntity ent: newsEntityList) {
            NewsResponse res = newEntity2NewsResponse(ent);
            mapperResponse.add(res);
        }
        return mapperResponse;
    }
/*
    public NewsUpdateResponse newsEntity2NewsUpdateResponse(NewEntity newsEntity) {
        NewsUpdateResponse newsUpdateResponse = new NewsUpdateResponse();
        newsUpdateResponse.setName(newsEntity.getName());
        newsUpdateResponse.setContent(newsEntity.getContent());
        newsUpdateResponse.setImage(newsUpdateResponse.getImage());
        newsUpdateResponse.setCategoryId(newsUpdateResponse.getCategoryId());

        return newsUpdateResponse;
    }
*/
}
