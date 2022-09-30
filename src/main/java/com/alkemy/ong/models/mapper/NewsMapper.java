package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.entity.NewEntity;
import com.alkemy.ong.models.entity.OrganizationEntity;
import com.alkemy.ong.models.entity.SlideEntity;
import com.alkemy.ong.models.request.NewsRequest;
import com.alkemy.ong.models.response.NewsResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NewsMapper {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;
    public NewEntity newsRequest2NewEntity(NewsRequest news) {

        // get category
        Optional<CategoryEntity> OptionalCategoryEntity = categoryRepository.findById(news.getCategoryId());

        //mapper
        NewEntity entity = new NewEntity();
        entity.setImageUrl(slide.getImageUrl());
        entity.setText(slide.getText());
        entity.setSliceOrder(slide.getSlideOrder());
        entity.setOrganization(OptionalOrganizationEntity.get());
        entity.setOrganizationId(slide.getOrganizationId());

        return entity;
    }

    public NewsResponse newEntity2NewsResponse(NewEntity savedEntity) {
        SlideResponse response = new SlideResponse();
        response.setId(savedEntity.getId());
        response.setImageUrl(savedEntity.getImageUrl());
        response.setText(savedEntity.getText());
        response.setSlideOrder(savedEntity.getSliceOrder());
        response.setOrganization(organizationMapper.organizationsEntity2OrganizationsResponse(savedEntity.getOrganization()));
        response.setOrganizationId(savedEntity.getOrganizationId());
        response.setTimestamps(savedEntity.getTimestamp());
        return response;
    }
}
