package com.alkemy.ong.models.request;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class UserRequest {
    @NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    private String firstName;

    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    private String lastName;

    @NonNull
    @Email(message = "enter a correct email")
    private String email;

    @NonNull
    @NotEmpty(message = "the password can't be null")
    private String password;
}
