package com.pwee.eventmanagmentapp.controller;

import com.pwee.eventmanagmentapp.entity.Event;
import com.pwee.eventmanagmentapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

//    @PostMapping("/user/{userId}")
//    public Event createEvent(@RequestBody Event event, @PathVariable Long userId) {
//        return eventService.createEvent(event, userId);
//    }
}
