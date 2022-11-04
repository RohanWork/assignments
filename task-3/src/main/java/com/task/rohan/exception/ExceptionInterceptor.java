package com.task.rohan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(blankValueException.class)
	public ResponseEntity<CustomException> exceptionHandler(blankValueException ex, WebRequest wr){
		CustomException customException = new CustomException();
		customException.setcode(400);
		customException.setMessage(ex.getMessage());
		return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
	}
}
