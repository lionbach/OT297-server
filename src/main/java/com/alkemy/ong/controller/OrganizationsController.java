package com.alkemy.ong.controller;

import com.alkemy.ong.models.dto.OrganizationsBasicDTO;
import com.alkemy.ong.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("organization")
public class OrganizationsController {

    @Autowired // para iniciar servicio
    private OrganizationsService organizationsService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationsBasicDTO> getOne() {
        OrganizationsBasicDTO org =  organizationsService.getOne();
        return ResponseEntity.ok().body(org);
    }

}
