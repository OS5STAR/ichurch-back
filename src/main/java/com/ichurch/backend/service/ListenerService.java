package com.ichurch.backend.service;

import com.ichurch.backend.dto.Listener.ListenerCreationDTO;
import com.ichurch.backend.dto.Listener.ListenerViewDTO;
import com.ichurch.backend.model.Listener;
import com.ichurch.backend.model.SubEvent;
import com.ichurch.backend.repository.ListenerRepo;
import com.ichurch.backend.repository.SubEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListenerService {

    @Autowired
    private ListenerRepo listenerRepo;
    @Autowired
    private SubEventRepo subEventRepo;


    @Deprecated
    public ListenerViewDTO createListener(ListenerCreationDTO dto) {
        Listener listener = listenerRepo.save(ListenerCreationDTO.dtoToModel(dto));

        return ListenerViewDTO.modelToDTO(listener);
    }

    public String insertIntoSubEvent(UUID subEventId, ListenerCreationDTO dto) {
        SubEvent subEvent = subEventRepo.findById(subEventId).orElseThrow(() -> new IllegalArgumentException("Sub event not found"));
        Listener listener = listenerRepo.findByEmail(dto.getEmail()).orElse(null);

        if (listener == null) {
            listener = listenerRepo.save(ListenerCreationDTO.dtoToModel(dto));
        }

        if (subEvent.getListeners() == null || listener.getSubEvents() == null) {
            throw new RuntimeException("Something went wrong with the lists.");
        }

        listener.getSubEvents().add(subEvent);
        subEvent.getListeners().add(listener);

        subEventRepo.save(subEvent);
        listenerRepo.save(listener);

        return "Listener " + listener.getEmail() + " added to sub event " + subEvent.getId();
    }
}