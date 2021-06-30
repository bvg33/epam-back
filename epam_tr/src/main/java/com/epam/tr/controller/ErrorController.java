package com.epam.tr.controller;

import com.epam.tr.error.ErrorResponse;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.exceptions.InvalidFileException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InvalidFileException.class)
    public final ResponseEntity<Object> handleBindExceptions(Exception ex) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(details);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public final ResponseEntity<Object> handleInvalidCredentialsExceptions(Exception ex) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(details);
        return new ResponseEntity<>(errorResponse, UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(details);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}
