package com.ichurch.backend.controller;

import com.ichurch.backend.dto.Listener.ListenerCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventViewDTO;
import com.ichurch.backend.service.ListenerService;
import com.ichurch.backend.service.SubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/subevent")
public class SubEventController {

    @Autowired
    private SubEventService subEventService;
    @Autowired
    private ListenerService listenerService;

    @GetMapping(value = "/{subEventId}")
    public ResponseEntity<?> getSubEvent(@PathVariable UUID subEventId){
        return ResponseEntity.status(HttpStatus.OK).body(subEventService.getById(subEventId));
    }

    @PostMapping(value = "/{subEventId}")
    public ResponseEntity<?> insertListener(@RequestBody ListenerCreationDTO dto,
                                            @PathVariable UUID subEventId){
        return ResponseEntity.status(HttpStatus.OK).body(listenerService.insertIntoSubEvent(subEventId, dto));

    }
}
