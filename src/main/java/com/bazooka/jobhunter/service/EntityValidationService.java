package com.bazooka.jobhunter.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class EntityValidationService {
	
	public ResponseEntity<Map<String, String>> validateFields(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
			
		for (FieldError error : result.getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
			
		return ResponseEntity.badRequest().body(errors);
	}

}
