package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder encoder;

    public UserResponse userEntity2UserResponse(UserEntity userEntity, String token) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getIdUser());
        userResponse.setFirstName(userEntity.getFirstName());
        userResponse.setLastName(userEntity.getLastName());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setPassword(userEntity.getPassword());
        userResponse.setToken(token);
        return userResponse;
    }

    public UserEntity userRequest2UserEntity(UserRequest userRequest, Set<RoleEntity> role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(encoder.encode(userRequest.getPassword()));
        userEntity.setRoleEntityId(role);
        return userEntity;
    }
}
