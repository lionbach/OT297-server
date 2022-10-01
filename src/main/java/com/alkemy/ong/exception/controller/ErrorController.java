package com.alkemy.ong.exception.controller;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.exception.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(GenericException ex){
        ErrorResponse errorBody = new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getErrors());
        ResponseEntity response = new ResponseEntity<>(errorBody, ex.getStatus());
        return response;
    }

}
