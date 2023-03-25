package com.parade.paradeproject.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HttpForwardException extends RuntimeException{

    private int originErrorCode;


    public HttpForwardException(String errorMessage, int originErrorCode){
        super(errorMessage);
        this.originErrorCode = originErrorCode;
    }

}
