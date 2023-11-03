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
//    private List<Event> eventRepository = new ArrayList<>();

//    public Event createEvent(Event event, Long userId) {
//        User user = userService.getUserById(userId);
//        event.setCoordinator(user);
//
//        // Simulating saving to a database (storing in a collection in this case)
//        Event savedEvent = saveEvent(event);
//
//        return savedEvent;
//    }

}
