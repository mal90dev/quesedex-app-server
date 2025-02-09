package com.quesedex.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	 
	public ResourceNotFoundException(String mensaje)
	{
		super(mensaje);
	}
	
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
