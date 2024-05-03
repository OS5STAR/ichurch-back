package com.ichurch.backend.enums;

public enum EventStatus {

    DRAFT("Draft"),
    SCHEDULED("Scheduled"),
    RESCHEDULED("Rescheduled"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    private final String label;

    EventStatus(String label){
        this.label = label;
    }
}