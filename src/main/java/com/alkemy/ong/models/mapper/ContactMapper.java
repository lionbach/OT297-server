package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.ContactEntity;
import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContactMapper {
    public ContactEntity contactRequest2ContactEntity(ContactRequest contactRequest) throws IOException {

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setName(contactRequest.getName());
        contactEntity.setPhone(contactRequest.getPhone());
        contactEntity.setEmail(contactRequest.getEmail());
        contactEntity.setMessage(contactRequest.getMessage());

        return contactEntity;
    }

    public ContactResponse contactEntity2ContactResponse(ContactEntity contactEntity) throws IOException {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contactEntity.getId());
        contactResponse.setName(contactEntity.getName());
        contactResponse.setPhone(contactEntity.getPhone());
        contactResponse.setEmail(contactEntity.getEmail());
        contactResponse.setMessage(contactEntity.getMessage());
        contactResponse.setTimestamps(contactEntity.getTimestamps());
        return contactResponse;
    }
}
