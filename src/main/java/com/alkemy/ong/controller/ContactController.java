package com.alkemy.ong.controller;


import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> save(@Valid @RequestBody ContactRequest contactRequest) throws IOException {
        ContactResponse contactSavedResponse  = contactService.save(contactRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactSavedResponse);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAll(){
        List<ContactResponse> contacts = contactService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }
}
