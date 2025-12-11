package com.jsp.phasezero_catalog_service.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

public class ResourceConflictException extends ResponseStatusException{
	public ResourceConflictException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }

}
