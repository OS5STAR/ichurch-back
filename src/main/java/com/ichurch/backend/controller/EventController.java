package com.ichurch.backend.controller;

import com.ichurch.backend.dto.Event.EventCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.service.EventService;
import com.ichurch.backend.service.SubEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(value = "/api/event")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SubEventService subEventService;

    @GetMapping(value = "/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable UUID eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(eventId));
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@Valid @RequestBody EventCreationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto));
    }

    @PatchMapping(value = "/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable UUID eventId,
                                         @Valid @RequestBody EventCreationDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(eventId, dto));
    }

    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable UUID eventId){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.deleteEvent(eventId));
    }

}
