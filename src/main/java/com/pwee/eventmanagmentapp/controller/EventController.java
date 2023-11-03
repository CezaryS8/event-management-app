package com.pwee.eventmanagmentapp.controller;

import com.pwee.eventmanagmentapp.service.EventService;
import com.pwee.eventmanagmentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/user/{userId}")
    public createEvent(@RequestBody Event event, @PathVariable userId ) {
        return eventService.createEvent(event, userId);
    }
}
