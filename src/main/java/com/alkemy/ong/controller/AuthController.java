package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.request.UserLoginRequest;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.response.UserLoginResponse;
import com.alkemy.ong.models.response.UserRegisterResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> signUp(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRegisterRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> signIn(@Valid @RequestBody @NotNull UserLoginRequest userLoginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
    }
}
