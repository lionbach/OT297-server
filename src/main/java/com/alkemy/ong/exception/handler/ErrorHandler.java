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
        List<String> errorDefaultMessages = new ArrayList<>();
        //create 1 line for any message
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorDefaultMessages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        // Esto no genera nada. No se que se espera de estas lineas
        //create 1 line for all errors
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errorDefaultMessages.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        ErrorListResponse errorListResponse = new ErrorListResponse(HttpStatus.BAD_REQUEST, errorDefaultMessages, ex.getLocalizedMessage() );
        ResponseEntity<Object> response = handleExceptionInternal(ex, errorListResponse, headers, errorListResponse.getStatus(), request);
        return response;
    }

    // this method capture exceptions generate with:
    // throw new GenericException(HttpStatus.YourStatus, "your default error message", "your long error message");
    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(GenericException ex){
        ErrorResponse errorBody = new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getError());
        ResponseEntity response = new ResponseEntity<>(errorBody, ex.getStatus());
        return response;
    }



}
