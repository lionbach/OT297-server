package com.alkemy.ong.service;

import com.alkemy.ong.models.request.UserUpdateRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.models.response.UserUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    ResponseEntity<Void> delete(Long id, String token);

    //UserUpdateResponse update(Long id, UserUpdateRequest userUpdateRequest) throws IOException;
    ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest, String token) throws IOException;
}
