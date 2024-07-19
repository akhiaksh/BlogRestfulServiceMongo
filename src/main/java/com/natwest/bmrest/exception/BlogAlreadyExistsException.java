package com.natwest.bmrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="Blog Details Already Exists ....")
public class BlogAlreadyExistsException extends Exception {

	public BlogAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
