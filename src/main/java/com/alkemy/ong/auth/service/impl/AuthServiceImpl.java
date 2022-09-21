package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.security.JwtTokenProvider;
import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.exception.JwtBadRequestException;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.RoleEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Set;

public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(@NotNull UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExistException(userRequest.getEmail());
        }
        Set<RoleEntity> roleEntities = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
        if (roleEntities.isEmpty()) {
            throw new NullPointerException();
        }
        UserEntity userEntity = userMapper.userRequest2UserEntity(userRequest, roleEntities);
        userEntity = userRepository.save(userEntity);
        UserResponse userResponse = userMapper.userEntity2UserResponse(userEntity,
                jwtTokenProvider.generateToken(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()))));
        return userResponse;
    }
}
