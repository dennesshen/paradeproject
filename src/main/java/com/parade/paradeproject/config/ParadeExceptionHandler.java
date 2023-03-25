package com.parade.paradeproject.config;

import com.parade.paradeproject.util.exception.HttpForwardException;
import com.parade.paradeproject.util.exception.handler.GenericExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = {"com.parade.paradeproject.slide",
                                      "com.parade.paradeproject.note"})
public class ParadeExceptionHandler extends GenericExceptionHandler{

    @ExceptionHandler(value = {HttpForwardException.class})
    public ResponseEntity<?> httpFordwardException(HttpForwardException ex) {

        Map<String, String> data = new LinkedHashMap<>();

        data.put("data", null);
        data.put("status", String.valueOf(ex.getOriginErrorCode()));
        data.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getOriginErrorCode()).body(data);
    }


}
