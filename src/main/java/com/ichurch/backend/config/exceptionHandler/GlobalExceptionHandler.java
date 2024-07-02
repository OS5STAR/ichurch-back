package com.ichurch.backend.config.exceptionHandler;

import com.ichurch.backend.customExceptions.ElementNotFoundException;
import com.ichurch.backend.customExceptions.RedundancyException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleNoHandlerFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessageBuilder("Resource not found", "Invalid Endpoint", request.getRequestURL()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleIllegalArgsException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessageBuilder(ex.getMessage(), "Illegal Arguments", request.getRequestURL()));
    }

    @ExceptionHandler({ElementNotFoundException.class, RedundancyException.class})
    public ResponseEntity<ExceptionMessageBuilder> handleElementNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessageBuilder(ex.getMessage(), "Element Error", request.getRequestURL()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessageBuilder> handleMethodArgsNotValidExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessageBuilder(ex, "Invalid Parameters", request.getRequestURL()));
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class, AuthenticationException.class})
    public ResponseEntity<ExceptionMessageBuilder> handleCredentials(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionMessageBuilder(ex.getMessage(), "Account Validation Error", request.getRequestURL()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageBuilder> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionMessageBuilder(ex.toString(), "Internal Server Error", request.getRequestURL()));
    }

}



