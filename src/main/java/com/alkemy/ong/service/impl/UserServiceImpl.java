package com.alkemy.ong.service.impl;

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

    @Override
    public List<UserResponse> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.userEntityList2UserResponseList(userEntities);
    }
}
