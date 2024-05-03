package com.ichurch.backend.controller;

import com.ichurch.backend.dto.Event.EventCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.service.EventService;
import com.ichurch.backend.service.SubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SubEventService subEventService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody EventCreationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto));
    }

    @PostMapping(value = "/{eventId}/subevent")
    public ResponseEntity<?> addSubEvent(@RequestBody SubEventCreationDTO dto,
                                         @PathVariable("eventId") UUID eventId){
        return ResponseEntity.status(HttpStatus.CREATED).body(subEventService.create(dto, eventId));
    }

    @GetMapping(value = "/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable UUID eventId) {

        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(eventId));
    }

}
