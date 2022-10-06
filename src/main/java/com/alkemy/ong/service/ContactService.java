package com.alkemy.ong.service;

import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;

import java.io.IOException;

public interface ContactService {

    ContactResponse save(ContactRequest contactRequest) throws IOException;
}
