package com.ichurch.backend.controller;

import com.ichurch.backend.dto.Event.AllEventViewDTO;
import com.ichurch.backend.dto.Event.EventCreationDTO;
import com.ichurch.backend.dto.Event.EventViewDTO;
import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.service.EventService;
import com.ichurch.backend.service.FileService;
import com.ichurch.backend.service.SubEventService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
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
    public ResponseEntity<EventViewDTO> getEventById(@PathVariable UUID eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(eventId));
    }

    @GetMapping
    public ResponseEntity<AllEventViewDTO> getAllEvents(@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable,
                                                        @RequestParam(required = false, defaultValue = "false") boolean fetchAll,
                                                        @RequestParam(required = false, defaultValue = "", value = "q") String term){
        if(fetchAll){
            pageable = Pageable.unpaged();
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents(pageable,term));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EventViewDTO> create(@Valid @RequestBody EventCreationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto));
    }

    @PatchMapping(value = "/{eventId}")
    public ResponseEntity<EventViewDTO> updateEvent(@PathVariable UUID eventId,
                                         @Valid @RequestBody EventCreationDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(eventId, dto));
    }

    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID eventId){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.deleteEvent(eventId));
    }

}
