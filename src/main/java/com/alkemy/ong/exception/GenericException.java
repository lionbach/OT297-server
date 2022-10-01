package com.alkemy.ong.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class GenericException extends RuntimeException {
    private HttpStatus status;
    private String error;

    public GenericException(HttpStatus status, String message, String error) {
        super(message);
        this.status = status;
        this.error = error;
    }
}
