package com.parade.paradeproject.util.exception.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.parade.paradeproject.util.exception.ParameterException;

public class GenericExceptionHandler {
	
	
	@ExceptionHandler(value = {NoSuchElementException.class, 
							   ParameterException.class})
	public ResponseEntity<?> dealException(Exception ex) {

		Map<String, String> data = new LinkedHashMap<>();
		
		data.put("data", null);
		data.put("status", "400");
		data.put("message", ex.getMessage());
				
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	}
	
}
