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

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token) {
        userService.delete(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(@Valid @PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userUpdateRequest));
    }
}
