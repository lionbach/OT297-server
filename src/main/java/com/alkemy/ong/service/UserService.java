package com.alkemy.ong.service;

import com.alkemy.ong.models.request.UserUpdateRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.models.response.UserUpdateResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    void delete(String token);

    UserUpdateResponse update(Long id, UserUpdateRequest userUpdateRequest) throws IOException;
}
