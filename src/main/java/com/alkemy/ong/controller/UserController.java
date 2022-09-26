package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.UserUpdateRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.models.response.UserUpdateResponse;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
        return userService.delete(id, token);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(@Valid @PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest, @RequestHeader(name = "Authorization") String token) throws IOException {
        //return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userUpdateRequest));
        return userService.update(id, userUpdateRequest, token);
    }
}
