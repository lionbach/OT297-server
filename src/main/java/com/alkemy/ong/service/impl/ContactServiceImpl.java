package com.alkemy.ong.service.impl;


import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.models.entity.ContactEntity;
import com.alkemy.ong.models.mapper.ContactMapper;
import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ContactResponse> getAll() {
        List<ContactEntity> listContactEntity =  contactRepository.findAll();
        List<ContactResponse> listContactResponse = new ArrayList<>();
        for (ContactEntity entity : listContactEntity) {
            try {
                listContactResponse.add(contactMapper.contactEntity2ContactResponse(entity));
            } catch (IOException e) {
                //throw new RuntimeException(e);
                throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "error in mapper", "error in mapper ContactMapper.contactEntity2ContactResponse(entity)");
            }
        }
        return listContactResponse;
    }
}
