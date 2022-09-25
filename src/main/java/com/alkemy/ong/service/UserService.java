package com.alkemy.ong.service;

import com.alkemy.ong.models.response.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAll();

    void delete(String token);
}
