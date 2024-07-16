package com.example.toolrental.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /*
    * Handles validation errors
    * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        logger.error(errors.toString(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex){
        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .toList();
        logger.error(errors.toString(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /*
    * Handles missing entity exception from DAO
    * */
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleEntityNotFound(NoSuchElementException exception) {
        String errorMessage = "Entity not found error: " + exception.getMessage();
        logger.error(errorMessage, exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    // Other exceptions can be added here

}
