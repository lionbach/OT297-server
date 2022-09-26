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
    public void delete(String token) {
        Long id = authService.getUserEntityByToken(token).getId();
        System.out.println(id);
        System.out.println("in UserServieImpl delete(token)");
        userRepository.deleteById(id);
    }

    @Override
    public UserUpdateResponse update(Long id, UserUpdateRequest userUpdateRequest) throws IOException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        if (userUpdateRequest.getFirstName() != null && !userUpdateRequest.getFirstName().isEmpty() && !userUpdateRequest.getFirstName().isBlank()) {
            userEntity.setFirstName(userUpdateRequest.getFirstName());
        }
        if (userUpdateRequest.getLastName() != null && !userUpdateRequest.getLastName().isEmpty() && !userUpdateRequest.getLastName().isBlank()) {
            userEntity.setLastName(userUpdateRequest.getLastName());
        }
        if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().isEmpty() && !userUpdateRequest.getPassword().isBlank()) {
            userEntity.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (userUpdateRequest.getPhoto() != null && !userUpdateRequest.getPhoto().isEmpty() && !userUpdateRequest.getPhoto().isBlank()) {
            userEntity.setPhoto(userUpdateRequest.getPhoto());
        }
        userEntity = userRepository.save(userEntity);
        UserUpdateResponse userUpdateResponse = userMapper.userEntity2UserUpdateResponse(userEntity);
        return userUpdateResponse;
    }
}
