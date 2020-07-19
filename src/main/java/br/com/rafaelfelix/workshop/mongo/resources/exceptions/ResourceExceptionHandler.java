package br.com.rafaelfelix.workshop.mongo.resources.exceptions;

import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.rafaelfelix.workshop.mongo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<?> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
        	   .body(new StandardError(new Date(), HttpStatus.NOT_FOUND.value(), "Not Found", exception.getMessage(), request.getRequestURI()));
    }
	
	@ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> runtime(NullPointerException exception, HttpServletRequest request) {
		return ResponseEntity.badRequest()
    	   .body(new StandardError(new Date(), HttpStatus.BAD_REQUEST.value(), "Param missing", exception.getMessage(), request.getRequestURI()));
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<?> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ValidationError validationError = new ValidationError(
				new Date(), 
				HttpStatus.UNPROCESSABLE_ENTITY.value(), 
				"Validation Error", 
				"Some required fields are missing, please check bellow...", 
				request.getRequestURI());
		for(FieldError error : exception.getBindingResult().getFieldErrors()) {
			validationError.addError(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(validationError);
    }
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> methodNotAllowed(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
		return ResponseEntity.badRequest()
    	   .body(new StandardError(new Date(), 
    			   HttpStatus.METHOD_NOT_ALLOWED.value(), 
    			   "Method not allowed for this URI", 
    			   exception.getMessage(), 
    			   request.getRequestURI()));
    }
	
	@ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<?> dateTimeParse(DateTimeParseException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	   .body(new StandardError(new Date(), 
    			   HttpStatus.INTERNAL_SERVER_ERROR.value(), 
    			   "DateTime parse error", 
    			   exception.getMessage(), 
    			   request.getRequestURI()));
    }
	
	@ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<?> runtime(RuntimeException exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    	   .body(new StandardError(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Generic Error", exception.getMessage(), request.getRequestURI()));
    }
}
