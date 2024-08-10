package com.ichurch.backend.config.exceptionHandler;


import lombok.Data;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ExceptionMessageBuilder {

    private String error;
    private String message;
    private StringBuffer URL;
    private Timestamp errorTime = new Timestamp(System.currentTimeMillis());
    private Object stack = "Stack Trace not given";
    private List<messageObj> validationError;

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

    public ExceptionMessageBuilder(MethodArgumentNotValidException ex, String error, StringBuffer URI) {
        List<messageObj> listErrors = new ArrayList<>();
        for(var er : ex.getBindingResult().getAllErrors()){
            if(er.getCodes() != null) {
                listErrors.add(new messageObj(er.getCodes()[1].substring(er.getCodes()[1].indexOf(".")+1), er.getDefaultMessage()));
            }
        }
        this.validationError = listErrors;
        this.message = "Request validation error.";
        this.error = error;
        this.URL = URI;
    }

    @Data
    private static class messageObj{
        private String fieldName;
        private String errorCause;

        private messageObj(String fieldName, String errorCause){
            this.fieldName = fieldName;
            this.errorCause = errorCause;
        }
    }

}

