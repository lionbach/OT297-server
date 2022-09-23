package com.alkemy.ong.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserLoginResponse {
    private String email;
    private String token;
}
