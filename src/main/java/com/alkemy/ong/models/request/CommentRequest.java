package com.alkemy.ong.models.request;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CommentRequest {

    @NotNull(message = "cannot be null")
    private Long userId;

    @NotNull(message = "cannot be null")
    private Long newsId;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String body;
}
