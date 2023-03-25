package com.parade.paradeproject.util.exception;

public class SystemServiceException extends RuntimeException{

    public SystemServiceException(){

    }

    public SystemServiceException(String errorMessage){
        super(errorMessage);
    }

}
