package com.example.assignmnet1_webservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
@ControllerAdvice
public class BadRequestException extends RuntimeException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> NotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
