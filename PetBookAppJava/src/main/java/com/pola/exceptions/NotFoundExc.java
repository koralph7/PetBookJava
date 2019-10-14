package com.pola.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExc extends RuntimeException{

	 public NotFoundExc() {
	        super();
	    }

	    public NotFoundExc(String message) {
	        super(message);
	    }

	    public NotFoundExc(String message, Throwable cause) {
	        super(message, cause);
	    }
}
