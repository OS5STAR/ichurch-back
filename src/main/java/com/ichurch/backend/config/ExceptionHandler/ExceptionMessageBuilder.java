package com.ichurch.backend.config.ExceptionHandler;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExceptionMessageBuilder {

    private String error;
    private String message;
    private StringBuffer URL;
    private Timestamp errorTime = new Timestamp(System.currentTimeMillis());
    private Object stack = "Stack Trace not given";

    public ExceptionMessageBuilder(String message, String error, Timestamp errorTime, StackTraceElement stack) {
        this.message = message;
        this.error = error;
        this.stack = stack;
    }

    public ExceptionMessageBuilder(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public ExceptionMessageBuilder(String message, String error, StringBuffer URI) {
        this.message = message;
        this.error = error;
        this.URL = URI;
    }

    public ExceptionMessageBuilder(String message, String error, StackTraceElement stack) {
        this.message = message;
        this.error = error;
        this.stack = stack;
    }

}

