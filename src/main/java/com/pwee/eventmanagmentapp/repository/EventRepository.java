package com.pwee.eventmanagmentapp.repository;

import com.pwee.eventmanagmentapp.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {

    public Event findEventById(Long eventId);
    public List<Event> findAllEvents();
    public Event saveEvent(Event event, Long userId);
    public void deleteEventById(Long eventId);

}
