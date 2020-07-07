package com.bazooka.jobhunter.exceptions;

public class ResourceNotFoundResponse {
	
	private String message;
	
	public ResourceNotFoundResponse(String message) {
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
