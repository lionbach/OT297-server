package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.OrganizationRequest;
import com.alkemy.ong.models.response.OrganizationBasicResponse;
import com.alkemy.ong.models.response.OrganizationResponse;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired // para iniciar servicio
    private OrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationBasicResponse>> getOrganizations() {
        List<OrganizationBasicResponse> org =  organizationService.getOrganizations();
        return ResponseEntity.ok().body(org);
    }

    @PostMapping("/public")
    public ResponseEntity<OrganizationResponse> save(@RequestBody OrganizationRequest organization) {
        OrganizationResponse savedOrganization = organizationService.save(organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
    }


}
