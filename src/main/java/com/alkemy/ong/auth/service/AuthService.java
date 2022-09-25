package com.alkemy.ong.auth.service;

import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.response.UserLoginResponse;
import com.alkemy.ong.models.response.UserRegisterResponse;
import com.alkemy.ong.models.response.UserResponse;

public interface AuthService {
    public UserRegisterResponse register(UserRegisterRequest user);

    public UserLoginResponse login(String email, String password) throws Exception;

    public UserResponse userAuth(String token);

    public UserEntity getUserEntityByToken(String token);
}
