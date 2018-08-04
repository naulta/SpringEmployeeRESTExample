package com.kenzanexample.employeedemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
public class ErrorInformation {
    private String message;
    private String exceptionString;
    public ErrorInformation(String message, Exception exception) {
        this.message = message;
        if (exception != null) {
          this.exceptionString = exception.getLocalizedMessage();
        }
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setException(String exception) {
        this.exceptionString = exception;
    }
    
    @JsonInclude(Include.NON_NULL)
    public String getException() {
        return exceptionString;
    }
}