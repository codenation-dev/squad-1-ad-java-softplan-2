package br.com.codenation.squad1.util.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> validation(Exception e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.PRECONDITION_FAILED.value(), e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(),
				request.getRequestURI());

		e.getBindingResult().getFieldErrors().stream().forEach(x -> err.addError(x.getField(), x.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

}
