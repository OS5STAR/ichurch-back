package com.ichurch.backend.controller;

import com.ichurch.backend.dto.Listener.ListenerCreationDTO;
import com.ichurch.backend.dto.Speaker.SpeakerCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventViewDTO;
import com.ichurch.backend.service.ListenerService;
import com.ichurch.backend.service.SpeakerService;
import com.ichurch.backend.service.SubEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/subevent")
@Validated
public class SubEventController {

    @Autowired
    private SubEventService subEventService;
    @Autowired
    private ListenerService listenerService;
    @Autowired
    private SpeakerService speakerService;


    @GetMapping(value = "/{subEventId}")
    public ResponseEntity<?> getSubEvent(@PathVariable UUID subEventId) {
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.getById(subEventId));
    }

    @PostMapping(value = "/{subEventId}/listener")
    public ResponseEntity<?> insertListener(@Valid @RequestBody ListenerCreationDTO dto,
                                            @PathVariable UUID subEventId) {
        return ResponseEntity.status(HttpStatus.OK).body(listenerService.insertIntoSubEvent(subEventId, dto));
    }

    @PostMapping(value = "/{subEventId}/speaker")
    public ResponseEntity<?> insertSpeaker(@Valid @RequestBody SpeakerCreationDTO dto,
                                            @PathVariable UUID subEventId) {
        return ResponseEntity.status(HttpStatus.OK).body(speakerService.insertIntoSubEvent(subEventId, dto));
    }

    @PatchMapping(value = "/{subEventId}")
    public ResponseEntity<?> updateSubEvent(@Valid @RequestBody SubEventCreationDTO dto,
                                            @PathVariable UUID subEventId){
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.updateSubEvent(subEventId, dto));
    }

    @DeleteMapping(value = "{subEventId}")
    public ResponseEntity<?> deleteSubEvent(@PathVariable UUID subEventId){
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.deleteSubEvent(subEventId));
    }

}
