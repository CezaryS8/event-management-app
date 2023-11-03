package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.entity.Event;
import com.pwee.eventmanagmentapp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final UserService userService;

    // Mocking an event repository (for demonstration purposes)
    private List<Event> eventRepository = new ArrayList<>();

    public Event createEvent(Event event, Integer userId) {
        User user = userService.getUserById(userId);
        event.setCoordinator(user);

        // Simulating saving to a database (storing in a collection in this case)
        Event savedEvent = saveEvent(event);

        return savedEvent;
    }

    private Event saveEvent(Event event) {
        // Simulate saving the event to the repository (for demonstration)
        event.setId(eventRepository.size() + 1); // Simulating ID generation
        eventRepository.add(event);
        return event;
    }
}