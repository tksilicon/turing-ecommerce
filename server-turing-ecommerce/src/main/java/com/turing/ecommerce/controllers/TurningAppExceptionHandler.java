package com.turing.ecommerce.controllers;

import java.util.AbstractMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.turing.ecommerce.exceptions.DepartmentNotFoundException;



@RestControllerAdvice
public class TurningAppExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(TurningAppExceptionHandler.class);

	/**
	 * Global Exception handler for all exceptions.
	 */
	@ExceptionHandler
	public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception exception) {
		// general exception
		LOG.error("Exception: Unable to process this request. ", exception);
		AbstractMap.SimpleEntry<String, String> response = new AbstractMap.SimpleEntry<>("message",
				"Unable to process this request.");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(value = DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handle(DepartmentNotFoundException exception) {
	      return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
	   }

}
