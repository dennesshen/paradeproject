package com.parade.paradeproject.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parade.paradeproject.util.exception.handler.GenericExceptionHandler;

@RestControllerAdvice(basePackages = {"com.parade.paradeproject.slide",
                                      "com.parade.paradeproject.note"})
public class GlobalExceptionHandler extends GenericExceptionHandler{

    
}
