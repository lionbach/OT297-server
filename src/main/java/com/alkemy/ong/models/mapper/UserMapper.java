package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.response.UserRegisterResponse;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.models.response.UserUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder encoder;

    public UserRegisterResponse userEntity2UserRegisterResponse(UserEntity userEntity, String token) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(userEntity.getId());
        userRegisterResponse.setFirstName(userEntity.getFirstName());
        userRegisterResponse.setLastName(userEntity.getLastName());
        userRegisterResponse.setEmail(userEntity.getEmail());
        userRegisterResponse.setPassword(userEntity.getPassword());
        userRegisterResponse.setToken(token);
        return userRegisterResponse;
    }

    public UserEntity userRegisterRequest2UserEntity(UserRegisterRequest userRegisterRequest, Set<RoleEntity> role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRegisterRequest.getFirstName());
        userEntity.setLastName(userRegisterRequest.getLastName());
        userEntity.setEmail(userRegisterRequest.getEmail());
        userEntity.setPassword(encoder.encode(userRegisterRequest.getPassword()));
        userEntity.setRoleEntityId(role);
        return userEntity;
    }

    public UserResponse userEntity2UserResponse(UserEntity user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .photo(user.getPhoto())
                .id(user.getId())
                .build();
    }

    public List<UserResponse> userEntityList2UserResponseList(List<UserEntity> userEntities) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            userResponseList.add(userEntity2UserResponse(entity));
        }
        return userResponseList;
    }


    public UserUpdateResponse userEntity2UserUpdateResponse(UserEntity userEntity) {
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setFirstName(userEntity.getFirstName());
        userUpdateResponse.setLastName(userEntity.getLastName());
        userUpdateResponse.setPassword(userEntity.getPassword());
        userUpdateResponse.setPhoto(userEntity.getPhoto());
        return userUpdateResponse;
    }
}
