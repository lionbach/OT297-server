package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.security.JwtTokenProvider;
import com.alkemy.ong.auth.service.AuthService;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRegisterRequest;
import com.alkemy.ong.models.response.UserLoginResponse;
import com.alkemy.ong.models.response.UserRegisterResponse;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.AuthenticationErrorEnum;
import com.alkemy.ong.utils.RoleEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
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
    public UserRegisterResponse register(@NotNull UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new EmailAlreadyExistException(userRegisterRequest.getEmail());
        }
        Set<RoleEntity> roleEntities = roleRepository.findByName(RoleEnum.USER.getFullRoleName());
        if (roleEntities.isEmpty()) {
            throw new NullPointerException();
        }
        UserEntity userEntity = userMapper.userRegisterRequest2UserEntity(userRegisterRequest, roleEntities);
        userEntity = userRepository.save(userEntity);
        UserRegisterResponse userRegisterResponse = userMapper.userEntity2UserRegisterResponse(userEntity,
                jwtTokenProvider.generateToken(authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userRegisterRequest.getEmail(), userRegisterRequest.getPassword()))));
        return userRegisterResponse;
    }
/*EL login en el caso de ingresar un password incorrecto, tira un ok: false,pero con 200*/
    @Override
    public UserLoginResponse login(String email, String password) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new UserLoginResponse(email, jwtTokenProvider.generateToken(authentication));
        } catch (Exception e) {
            return new UserLoginResponse(AuthenticationErrorEnum.OK.name(), AuthenticationErrorEnum.FALSE.name());
        }
    }

    // this method is used for auth/me
    @Override
    @Transactional(readOnly = true)
    public UserResponse userAuth(String token) {
        token = token.replace("Bearer ", "");
        String email = jwtTokenProvider.getJWTUsername(token);
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the searched user does not exist"));
        return userMapper.userEntity2UserResponse(userEntity);
    }
}
