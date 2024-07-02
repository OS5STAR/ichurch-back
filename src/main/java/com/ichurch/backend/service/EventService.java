package com.ichurch.backend.service;

import com.ichurch.backend.customExceptions.ElementNotFoundException;
import com.ichurch.backend.dto.Event.AllEventViewDTO;
import com.ichurch.backend.dto.Event.EventCreationDTO;
import com.ichurch.backend.dto.Event.EventViewDTO;
import com.ichurch.backend.enums.EventStatus;
import com.ichurch.backend.model.Event;
import com.ichurch.backend.model.Listener;
import com.ichurch.backend.model.User;
import com.ichurch.backend.repository.EventRepo;
import com.ichurch.backend.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FileStorageService fileStorageService;

    public EventViewDTO getEventById(UUID eventId) {
        return EventViewDTO.modelToDto(eventRepo.findById(eventId).orElseThrow(() -> new ElementNotFoundException("Event not found")));
    }

    public AllEventViewDTO getAllEvents() {
        return AllEventViewDTO.allEventViewDTO(eventRepo.findAll().stream().map(EventViewDTO::modelToDto).collect(Collectors.toList()), eventRepo.count());

    }

    @SneakyThrows
    public EventViewDTO createEvent(EventCreationDTO dto) {
        if (isInvalid(dto)) {
            throw new IllegalArgumentException("Fields missing");
        }

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new ElementNotFoundException("Event creator must be a existing user"));

        dto.setImageUrl(fileStorageService.storeBase64Image(dto.getImageUrl()));
        Event event = EventCreationDTO.dtoToModel(dto);
        event.setCreatedBy(user);
        eventRepo.save(event);
        return EventViewDTO.modelToDto(event);
    }

    public EventViewDTO updateEvent(UUID eventId, EventCreationDTO dto) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new ElementNotFoundException("Event not found"));

        if (dto.getName() != null) {
            event.setName(dto.getName());
        }
        if (dto.getStartDate() != null) {
            event.setStartDate(dto.getStartDate());
        }
        if (dto.getEndDate() != null) {
            event.setEndDate(dto.getEndDate());
        }
        if (dto.getStatus() != null) {
            event.setStatus(dto.getStatus());
        }

        eventRepo.save(event);
        return EventViewDTO.modelToDto(event);
    }

    /**
     * REDUDANTE
     * Valida se o inicio do evento é antes do final do mesmo.
     * O Controller ja faz a validação
     */
    private boolean isInvalid(EventCreationDTO dto) {

        if (dto.getStartDate().after(dto.getEndDate())) {
            throw new IllegalArgumentException("Event start date cannot be greater than event end date");
        }

        return dto.getName() == null
                || dto.getStatus() == null
                || dto.getEndDate() == null;
    }

    /**
     * IGNORAR
     * A ser movida.
     */
    private void validateEventStatus() {

        LocalDateTime now = LocalDateTime.now();
        Timestamp today = Timestamp.valueOf(now);
        List<Event> allEvents = eventRepo.findAll();

        for (int i = 0; i < eventRepo.count(); i++) {
            Event eventToChange = eventRepo.findById(allEvents.get(i).getId()).orElseThrow(() -> new RuntimeException("MEGA EXCEPTION"));
            if (eventToChange.getStartDate().toLocalDateTime().isBefore(today.toLocalDateTime())) {
                eventToChange.setStatus(EventStatus.HAPPENING);
                eventRepo.save(eventToChange);
            }
        }
    }

    /**
     * MELHORAR RESPOSTA?
     * Deleta evento !?
     *
     * @param eventId
     * @return
     */
    @Transactional
    public Object deleteEvent(UUID eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new ElementNotFoundException("Event not found"));
        eventRepo.delete(event);

        return "OK";
    }
}
