package org.mgmt.system.exceptionhandler;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(
	      MethodArgumentNotValidException ex, HttpHeaders headers,
	      HttpStatus status, WebRequest request) {

		  Map<String, String> errors = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) ->{
				
				String fieldName = ((FieldError) error).getField();
				String message = error.getDefaultMessage();
				errors.put(fieldName, message);
			});
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

	  }	

	  
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<String> handleAlreadyPresentException(RecordAlreadyPresentException recordAlreadyPresentException)
	{
		return new ResponseEntity<String>(recordAlreadyPresentException.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(RecordNotFoundException recordNotFoundException)
	{
		return new ResponseEntity<String>(recordNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handleBadCredentialException(InvalidCredentialsException inValidCredentialsException)
	{
		return new ResponseEntity<String>(inValidCredentialsException.getMessage(), HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(EmptyListFoundException.class)
	public ResponseEntity<String> handleNoRecordFoundlException(EmptyListFoundException noRecordFoundException)
	{
		return new ResponseEntity<String>(noRecordFoundException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		
	}

}
