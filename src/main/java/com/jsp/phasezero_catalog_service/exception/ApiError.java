package com.jsp.phasezero_catalog_service.exception;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class ApiError {
	private int status;
    private String error;
    private String message;
    private Instant timestamp;
    private List<String> details;

    public ApiError() {
    	this.timestamp=Instant.now();
    }
    
    public ApiError(int status, String error, String message, List<String> details) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }
}
