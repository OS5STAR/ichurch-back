package com.ichurch.backend.customExceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String message){
        super(message);
    }
}
