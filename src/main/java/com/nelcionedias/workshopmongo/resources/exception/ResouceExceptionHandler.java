package com.nelcionedias.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelcionedias.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResouceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) //mostra que quando ocorrer a exceção é para tratar da forma abaixo
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus staus = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(),staus.value() , "Not Found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(staus).body(err);
	}
}
