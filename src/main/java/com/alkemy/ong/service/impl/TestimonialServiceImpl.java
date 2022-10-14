package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.TestimonialEntity;
import com.alkemy.ong.models.mapper.TestimonialMapper;
import com.alkemy.ong.models.request.TestimonialRequest;
import com.alkemy.ong.models.response.TestimonialPageResponse;
import com.alkemy.ong.models.response.TestimonialResponse;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utils.ClassUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.alkemy.ong.utils.ApiConstants.PATH_TESTIMONIALS;

@Service
public class TestimonialServiceImpl extends ClassUtil<TestimonialEntity, Long, TestimonialRepository> implements TestimonialService {
    @Autowired
    TestimonialMapper testimonialMapper;
    @Autowired
    TestimonialRepository testimonialRepository;

    @Override
    public TestimonialResponse save(TestimonialRequest request) {
        TestimonialEntity entity = testimonialMapper.testimonialRequest2TestimonialEntity(request);
        TestimonialEntity entitySaved = testimonialRepository.save(entity);
        TestimonialResponse response = testimonialMapper.testimonialEntity2TestimonialResponse(entitySaved);
        return response;
    }

    @Override
    public TestimonialResponse update(TestimonialRequest request, Long id) {
        TestimonialEntity entity = testimonialRepository.findById(id).orElseThrow();
        if (request.getImage() != null && !request.getImage().isEmpty() && !request.getImage().isBlank()) {
            entity.setImage(request.getImage());
        }
        entity.setName(request.getName());
        entity.setContent(request.getContent());
        TestimonialEntity entitySaved = testimonialRepository.save(entity);
        TestimonialResponse response = testimonialMapper.testimonialEntity2TestimonialResponse(entitySaved);
        return response;
    }

    @Override
    public void delete(Long id) {
        if (!testimonialRepository.existsById(id))
            throw new GenericException(HttpStatus.NOT_FOUND, "id not exist", "The testimonial to delete does not exist");
        testimonialRepository.deleteById(id);
    }

    @Override
    public TestimonialPageResponse pagination(Integer pageNumber) throws NotFoundException {
        if (pageNumber < 1) {
            throw new NotFoundException("Invalid resource");
        }
        Page<TestimonialEntity> page = getPage(pageNumber);
        String previous = getPrevious(PATH_TESTIMONIALS, pageNumber);
        String next = getNext(page, PATH_TESTIMONIALS, pageNumber);
        return testimonialMapper.testimonialEntityList2TestimonialPageResponse(page.getContent(), next, previous);
    }
}
