package com.alkemy.ong.models.request;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class UserLoginRequest {
    @NonNull
    @Email(message = "Enter a correct email")
    private String email;
    @NonNull
    @NotEmpty(message = "The password can't be empty")
    private String password;
}
