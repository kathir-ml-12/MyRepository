package org.mgmt.system.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
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
