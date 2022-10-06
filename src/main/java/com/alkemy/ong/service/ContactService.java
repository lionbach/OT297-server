package com.alkemy.ong.service;

import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;

import java.io.IOException;
import java.util.List;

public interface ContactService {

    ContactResponse save(ContactRequest contactRequest) throws IOException;

    List<ContactResponse> getAll();
}
