package com.ichurch.backend.controller;

import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.service.ListenerService;
import com.ichurch.backend.service.SpeakerService;
import com.ichurch.backend.service.SubEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/subevent")
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

    @PostMapping(value = "/{subEventId}")
    public ResponseEntity<?> addListener(@PathVariable UUID subEventId,
                                         @RequestParam(required = false) String listener,
                                         @RequestParam(required = false) String speaker) {
        if (listener != null && speaker != null) {
            return ResponseEntity.badRequest().body("You can only add either a listener or a speaker, not both.");
        }
        if (listener != null) {
            return ResponseEntity.ok(subEventService.addListener(subEventId, listener));
        }
        if (speaker != null) {
            return ResponseEntity.ok(subEventService.addSpeaker(subEventId, speaker));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping(value = "/{subEventId}")
    public ResponseEntity<?> updateSubEvent(@Valid @RequestBody SubEventCreationDTO dto,
                                            @PathVariable UUID subEventId) {
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.updateSubEvent(subEventId, dto));
    }

    @DeleteMapping(value = "{subEventId}")
    public ResponseEntity<?> deleteSubEvent(@PathVariable UUID subEventId) {
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.deleteSubEvent(subEventId));
    }

}
