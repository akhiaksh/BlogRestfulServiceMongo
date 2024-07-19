package com.natwest.bmrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND,reason="Blog Details Does Not Exists ....")
public class BlogNotFoundException extends Exception {

	public BlogNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
