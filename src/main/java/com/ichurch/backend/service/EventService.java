package com.ichurch.backend.service;

import com.ichurch.backend.CustomEx.ElementNotFoundException;
import com.ichurch.backend.dto.Event.EventCreationDTO;
import com.ichurch.backend.dto.Event.EventViewDTO;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    public EventViewDTO getEventById(UUID eventId) {
        return EventViewDTO.modelToDto(eventRepo.findById(eventId).orElseThrow(() -> new ElementNotFoundException("Event not found")));
    }

    public EventViewDTO createEvent(EventCreationDTO dto) {
        if(dto.getStartDate().after(dto.getEndDate())){
            throw new IllegalArgumentException("Event start date cannot be greater than event end date");
        }

        Event event = EventCreationDTO.dtoToModel(dto);

        eventRepo.save(event);

        return EventViewDTO.modelToDto(event);
    }
}
