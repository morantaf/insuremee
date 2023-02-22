package com.example.insuremee.controller;

import com.example.insuremee.exception.DuplicateEntityException;
import com.example.insuremee.exception.NoAccessException;
import com.example.insuremee.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = DuplicateEntityException.class)
    public ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoAccessException.class)
    public ResponseEntity<Object> handleNoAccessException(NoAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
