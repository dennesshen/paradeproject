package com.parade.paradeproject.util.exception;

public class ParameterException extends RuntimeException{

    public ParameterException() {
    }

    public ParameterException(String errormessage) {
        super(errormessage);
    }

}
