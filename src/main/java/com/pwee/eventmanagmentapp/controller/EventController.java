package com.pwee.eventmanagmentapp.controller;

import com.pwee.eventmanagmentapp.entity.Event;
import com.pwee.eventmanagmentapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
    @PostMapping("/user/{userId}")
    public Event createEvent(@RequestBody Event event, @PathVariable Long userId) {
        return eventService.createEvent(event, userId);
    }
    @DeleteMapping("/{eventId}")
    public void deleteEventById(@PathVariable Long eventId) {
        eventService.deleteEventById(eventId);
    }
    @PutMapping("/{eventId}")
    public Event updateEvent(@RequestBody Event event, @PathVariable Long userId) {
        return eventService.updateEvent(event, userId);
    }

}
