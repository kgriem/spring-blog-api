package com.cogent.blog.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.JstlUtils;

public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message){
        this.message=message;
        this.status=status;
    }

    public BlogApiException(String customMessage, HttpStatus status, String message){
        super(customMessage);
        this.status=status;
        this.message=message;
    }
}
