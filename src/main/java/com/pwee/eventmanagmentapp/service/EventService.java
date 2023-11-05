package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.entity.Event;
import com.pwee.eventmanagmentapp.exception.EventNotFoundException;
import com.pwee.eventmanagmentapp.exception.UserNotFoundException;
import com.pwee.eventmanagmentapp.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public Event getEventById(Long eventId) {
        Event event = eventRepository.findEventById(eventId);
        if(event == null) {
            throw new EventNotFoundException("Event doesn't exist!");
        }
        return event;
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }
    public Event createEvent(Event event, Long userId) {
        if(userService.getUserById(userId) == null) {
            throw new UserNotFoundException("User doesn't exist! Nothing created.");
        }
        return eventRepository.saveEvent(event, userId);
    }
    public void deleteEventById(Long eventId) {
        eventRepository.deleteEventById(eventId);
    }
    public Event updateEvent(Event event, Long userId) {
        if(eventRepository.findEventById(event.getId()) == null) {
            throw new EventNotFoundException("Event doesn't exist! Nothing updated.");
        }
        if(userService.getUserById(userId) == null) {
            throw new UserNotFoundException("User doesn't exist! Nothing updated.");
        }
        return eventRepository.saveEvent(event, userId);
    }
}
