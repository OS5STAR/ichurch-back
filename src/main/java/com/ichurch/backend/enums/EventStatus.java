package com.ichurch.backend.enums;

public enum EventStatus {

    DRAFT("Draft"),
    CREATED("Created"),
    SCHEDULED("Scheduled"),
    RESCHEDULED("Rescheduled"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    EventStatus(String label){
    }

}