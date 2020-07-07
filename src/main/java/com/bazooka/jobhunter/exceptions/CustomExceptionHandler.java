package com.bazooka.jobhunter.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResourceNotFoundResponse> handleResourceNotFound(ResourceNotFoundException exc) {
		ResourceNotFoundResponse response = new ResourceNotFoundResponse(exc.getMessage());
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler
	public ResponseEntity<UsernameAlreadyExistsResponse> handleUsernameAlreadyExists(UsernameAlreadyExistsException exc) {
		UsernameAlreadyExistsResponse response = new UsernameAlreadyExistsResponse(exc.getMessage());
		return ResponseEntity.badRequest().body(response);
	}

}