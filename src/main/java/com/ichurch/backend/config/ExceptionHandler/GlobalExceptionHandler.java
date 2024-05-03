package com.ichurch.backend.config.ExceptionHandler;

import com.ichurch.backend.CustomEx.ElementNotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;
import java.util.Arrays;

@ControllerAdvice
@ComponentScan
public class GlobalExceptionHandler {


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleNoHandlerFoundException(NoResourceFoundException ex) {

        ExceptionMessageBuilder exceptionMessageBuilder = new ExceptionMessageBuilder("Resource not found: " + ex.getResourcePath(),"Invalid Endpoint");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessageBuilder);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleIllegalArgsException(IllegalArgumentException ex) {

        ExceptionMessageBuilder exceptionMessageBuilder = new ExceptionMessageBuilder(ex.getMessage(),"Illegal Arguments");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionMessageBuilder);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleElementNotFoundException(ElementNotFoundException ex) {
        ExceptionMessageBuilder exceptionMessageBuilder = new ExceptionMessageBuilder(ex.getMessage(), "Not Found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessageBuilder);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + e.getMessage());
    }

}



