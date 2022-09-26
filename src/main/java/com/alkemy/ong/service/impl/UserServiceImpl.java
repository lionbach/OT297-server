package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.request.UserUpdateRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.models.response.UserUpdateResponse;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.userEntityList2UserResponseList(userEntities);
    }

    @Override
    public ResponseEntity<Void> delete(Long id, String token) {
        ResponseEntity<Void> response;
        if (authService.roleValidator(id, token)){
            userRepository.deleteById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

    @Override
    public ResponseEntity<UserUpdateResponse> update(Long id, UserUpdateRequest userUpdateRequest, String token) throws IOException {
        ResponseEntity<UserUpdateResponse> response;
        if (authService.roleValidator(id, token)){
            UserEntity entity = userRepository.findById(id).orElseThrow();
            if (validInput(userUpdateRequest.getFirstName())) entity.setFirstName(userUpdateRequest.getFirstName());
            if (validInput(userUpdateRequest.getLastName())) entity.setLastName(userUpdateRequest.getLastName());
            if (validInput(userUpdateRequest.getPassword())) entity.setPassword(userUpdateRequest.getPassword());
            if (validInput(userUpdateRequest.getPhoto())) entity.setPhoto(userUpdateRequest.getPhoto());
            response = ResponseEntity.status(HttpStatus.OK).body(userMapper.userEntity2UserUpdateResponse(userRepository.save(entity)));
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

    public boolean validInput(String input){
        return (input != null && !input.isEmpty() && !input.isBlank());
    }

}
