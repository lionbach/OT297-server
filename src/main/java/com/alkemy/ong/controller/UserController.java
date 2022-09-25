package com.alkemy.ong.controller;

import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token){
        userService.delete(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
