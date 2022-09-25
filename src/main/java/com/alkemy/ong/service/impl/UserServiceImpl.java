package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthService authService;

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
}
