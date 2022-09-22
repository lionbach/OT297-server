package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unused")
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).get();
        System.out.println(user.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(user.getEmail(), user.getPassword(), mappRoles(user.getRoleEntityId()));
    }

    private Collection<? extends GrantedAuthority> mappRoles(Set<RoleEntity> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }

}
