package com.alkemy.ong.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequest {
    @ApiModelProperty(notes = "the new user firstname", example = "Jhon", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String firstName;

    @ApiModelProperty(notes = "the new user lastname", example = "Example", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String lastName;

    @ApiModelProperty(notes = "the new user email", example = "jhon@example.com", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @Email(message = "enter a valid email")
    private String email;

    @ApiModelProperty(notes = "the password", example = "12345", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String password;
}
