package com.alkemy.ong.auth.service;

import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;

public interface AuthService {
    public UserResponse register(UserRequest user);
}
