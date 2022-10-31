package com.alkemy.ong.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@Builder
public class UserLoginRequest {
    @ApiModelProperty(notes = "the email of user", example = "gisel-email@gmail.com", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    @Email(message = "Enter a valid email")
    private String email;

    @ApiModelProperty(notes = "the password of user", example = "12345", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String password;
}
