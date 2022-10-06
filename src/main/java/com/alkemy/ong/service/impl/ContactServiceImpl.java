package com.alkemy.ong.service.impl;


import com.alkemy.ong.models.entity.ContactEntity;
import com.alkemy.ong.models.mapper.ContactMapper;
import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public ContactResponse save(ContactRequest contactRequest) throws IOException {
        ContactEntity contactEntity =  contactMapper.contactRequest2ContactEntity(contactRequest);
        ContactEntity contactSavedEntity = contactRepository.save(contactEntity);
        ContactResponse contactResponse = contactMapper.contactEntity2ContactResponse(contactSavedEntity);
        return contactResponse;
    }
}
