package com.ivoyant.usermanagement.service;

import com.ivoyant.usermanagement.entity.Event;
import com.ivoyant.usermanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public Event createEvent(Event event){
        return eventRepository.save(event);
    }
}
