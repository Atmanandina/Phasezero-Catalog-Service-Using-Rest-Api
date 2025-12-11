package com.jsp.phasezero_catalog_service.exception;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(ResourceConflictException.class)
	    public ResponseEntity<ApiError> handleConflict(ResourceConflictException ex) {
	        ApiError err = new ApiError(HttpStatus.CONFLICT.value(), "Conflict", ex.getReason(), List.of(ex.getReason()));
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	    }

	    @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
	        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getReason(), List.of(ex.getReason()));
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
	        ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage(), List.of(ex.getMessage()));
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	    }

	    // Spring validation errors
	    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	    public final ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
	        List<String> details = ex.getBindingResult()
	                .getAllErrors()
	                .stream()
	                .map(err -> {
	                    if (err instanceof FieldError fe) {
	                        return fe.getField() + ": " + fe.getDefaultMessage();
	                    }
	                    return err.getDefaultMessage();
	                })
	                .collect(Collectors.toList());
	        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation Failed", "One or more fields are invalid", details);
	        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	    }
	}


