package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.request.UserLoginRequest;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.response.UserLoginResponse;
import com.alkemy.ong.models.response.UserRegisterResponse;
import com.alkemy.ong.models.response.UserResponse;
import io.swagger.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

// swagger
@Api(tags = "Auth", description = "User register and login")
// swagger end
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    // swagger
    @ApiOperation(value = "Register", notes = "Allows anyone to register as a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request - cannot be null - cannot be empty - cannot be blank - Enter a valid email"),
            @ApiResponse(code = 409, message = "Conflict - Email already exists")

    })
    // swagger end
    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserRegisterResponse> signUp(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRegisterRequest));
    }

    // swagger
    @ApiOperation(value = "Login", notes = "Allows user and admin to login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request - cannot be null - cannot be empty - cannot be blank - Enter a valid email")
    })
    // swagger end
    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserLoginResponse> signIn(@Valid @RequestBody @NotNull UserLoginRequest userLoginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword()));
    }

    // swagger
    @ApiOperation(value = "User info", notes = "Allows the user to get their information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Not found - the searched user does not exist")
    })
    // swagger end
    @GetMapping("/me")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserResponse> getUser(@ApiParam(value = "token", required = false, hidden = true) @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.userAuth(token));
    }
}
