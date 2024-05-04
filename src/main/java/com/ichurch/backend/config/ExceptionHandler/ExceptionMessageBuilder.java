package com.ichurch.backend.config.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class ExceptionMessageBuilder {

    private String message;
    private String error;
    private Timestamp errorTime = new Timestamp(System.currentTimeMillis());
    private Object stack = "Stack Trace not given";

    public ExceptionMessageBuilder(String message, String error, String errorTime, StackTraceElement stack){
        this.message = message;
        this.error = error;
        this.stack = stack;

    }

    public ExceptionMessageBuilder(String message, String error) {
        this.message = message;
        this.error = error;

    }

    public ExceptionMessageBuilder(String message, String error, StackTraceElement stack) {
        this.message = message;
        this.error = error;
        this.stack = stack;

    }
}

