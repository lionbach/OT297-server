package com.alkemy.ong.exception.handler;

import com.alkemy.ong.exception.GenericException;
import com.alkemy.ong.exception.response.ErrorListResponse;
import com.alkemy.ong.exception.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    // this method capture exceptions for the java annotations
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();
        //create 1 line for any message
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        //create 1 line for all errors
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorListResponse errorListResponse = new ErrorListResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        ResponseEntity<Object> response = handleExceptionInternal(ex, errorListResponse, headers, errorListResponse.getStatus(), request);
        return response;
    }

    // this method capture exceptions generate with:
    // throw new GenericException(HttpStatus.YourStatus, "your message here", "your error here");
    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(GenericException ex){
        ErrorResponse errorBody = new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getError());
        ResponseEntity response = new ResponseEntity<>(errorBody, ex.getStatus());
        return response;
    }



}
