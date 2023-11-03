package com.pwee.eventmanagmentapp.repository;

import com.pwee.eventmanagmentapp.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MockEventRepository implements EventRepository {

    private final Map<Long, Event> events = new HashMap<>();
    private Long eventIdCounter = 1L;

    @Override
    public Event findEventById(Long eventId) {
        return events.get(eventId);
    }

    @Override
    public List<Event> findAllEvents() {
        return new ArrayList<>(events.values());
    }

    @Override
    public Event saveEvent(Event event, Long userId) {
        if(event.getId() == null) {
            event.setId(eventIdCounter);
            event.setCoordinatorId(userId);
            eventIdCounter++;
        }
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public void deleteEventById(Long eventId) {
        events.remove(eventId);
    }

}
