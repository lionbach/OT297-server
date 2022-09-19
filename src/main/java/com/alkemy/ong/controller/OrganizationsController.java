package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.OrganizationsRequest;
import com.alkemy.ong.models.response.OrganizationsBasicResponse;
import com.alkemy.ong.models.response.OrganizationsResponse;
import com.alkemy.ong.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationsController {

    @Autowired // para iniciar servicio
    private OrganizationsService organizationsService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationsBasicResponse>> getOrganizations() {
        List<OrganizationsBasicResponse> org =  organizationsService.getOrganizations();
        return ResponseEntity.ok().body(org);
    }

    @PostMapping("/public")
    public ResponseEntity<OrganizationsResponse> save(@RequestBody OrganizationsRequest organization) {
        OrganizationsResponse savedOrganization = organizationsService.save(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
    }


}
