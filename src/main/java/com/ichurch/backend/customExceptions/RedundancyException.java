package com.ichurch.backend.customExceptions;

public class RedundancyException extends RuntimeException{

    public RedundancyException(String message){
        super(message);
    }
}
