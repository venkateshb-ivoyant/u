package com.ivoyant.usermanagement.controller;

import com.ivoyant.usermanagement.entity.Event;
import com.ivoyant.usermanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/event/")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("createEvent")
    public ResponseEntity<Event> createUser(@RequestBody Event event){
        return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
    }

}
