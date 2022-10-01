package com.alkemy.ong.models.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class UserRegisterRequest {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String firstName;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String lastName;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @Email(message = "enter a valid email")
    private String email;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String password;
}
