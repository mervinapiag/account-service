package com.exam.account_service.api.account.exception;

import com.exam.account_service.api.account.dto.StatusResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final int CUSTOMER_NOT_FOUND_STATUS = 401;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponse> handleValidation(MethodArgumentNotValidException ex) {

        Set<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toCollection(TreeSet::new));
        return badRequest(String.join("; ", messages));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StatusResponse> handleUnreadable(HttpMessageNotReadableException ex) {
        return badRequest("Malformed request payload");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StatusResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return badRequest("Invalid customer number");
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<StatusResponse> handleDuplicateEmail(DuplicateEmailException ex) {
        return badRequest("Email is already registered");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StatusResponse> handleIntegrity(DataIntegrityViolationException ex) {
        return badRequest("Email is already registered");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<StatusResponse> handleNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(CUSTOMER_NOT_FOUND_STATUS)
                .body(new StatusResponse(CUSTOMER_NOT_FOUND_STATUS, "Customer not found"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatusResponse> handleUnexpected(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StatusResponse(500, "Unexpected error"));
    }

    private ResponseEntity<StatusResponse> badRequest(String description) {
        return ResponseEntity.badRequest().body(new StatusResponse(400, description));
    }
}
