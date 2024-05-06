package com.ichurch.backend.service;

import com.ichurch.backend.customExceptions.ElementNotFoundException;
import com.ichurch.backend.dto.Speaker.SpeakerCreationDTO;
import com.ichurch.backend.model.Speaker;
import com.ichurch.backend.model.SubEvent;
import com.ichurch.backend.repository.SpeakerRepo;
import com.ichurch.backend.repository.SubEventRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepo speakerRepo;
    @Autowired
    private SubEventRepo subEventRepo;

    @Transactional
    public String insertIntoSubEvent(UUID subEventId, SpeakerCreationDTO dto) {

        SubEvent subEvent = subEventRepo.findById(subEventId).orElseThrow(() -> new ElementNotFoundException("Sub event not found"));
        Speaker speaker = speakerRepo.findByEmail(dto.getEmail()).orElse(null);

        if (!this.isValid(dto)) {
            throw new IllegalArgumentException("Listener is not valid");
        }

        if (speaker == null) {
            speaker = speakerRepo.save(SpeakerCreationDTO.dtoToModel(dto));
        }

        if (subEvent.getListeners() == null || speaker.getSubEvents() == null)
            throw new RuntimeException("Something went wrong with the lists");

        speaker.getSubEvents().add(subEvent);
        subEvent.getSpeakers().add(speaker);


        speakerRepo.save(speaker);
        subEventRepo.save(subEvent);


        return "Speaker " + speaker.getEmail() + " added to sub event " + subEvent.getId();
    }


    private boolean isValid(SpeakerCreationDTO dto) {

        if (dto.getAge() <= 0 || dto.getAge() > 110) {
            return false;
        }

        return true;
    }
}
