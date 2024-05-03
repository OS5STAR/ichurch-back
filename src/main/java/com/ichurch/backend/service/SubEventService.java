package com.ichurch.backend.service;

import com.ichurch.backend.CustomEx.ElementNotFoundException;
import com.ichurch.backend.dto.SubEvent.SubEventCreationDTO;
import com.ichurch.backend.dto.SubEvent.SubEventViewDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.SubEvent;
import com.ichurch.backend.repository.EventRepo;
import com.ichurch.backend.repository.SubEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubEventService {

    @Autowired
    private SubEventRepo subEventRepo;
    @Autowired
    private EventRepo eventRepo;


    public SubEventViewDTO create(SubEventCreationDTO dto, UUID eventId) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new ElementNotFoundException("Event not found"));
        dto.setEvent(event);

        SubEvent subEvent = SubEventCreationDTO.dtoToModel(dto);

        subEventRepo.save(subEvent);

        return SubEventViewDTO.modelToDTO(subEvent);
    }

    public SubEventViewDTO getById(UUID subEventId) {

        return SubEventViewDTO.modelToDTO(
                subEventRepo.findById(subEventId)
                        .orElseThrow(() -> new ElementNotFoundException("Sub event not found")));
    }
}
