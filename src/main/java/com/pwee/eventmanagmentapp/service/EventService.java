package com.pwee.eventmanagmentapp.service;

import com.pwee.eventmanagmentapp.dto.EventDTO;
import com.pwee.eventmanagmentapp.entity.Event;
import com.pwee.eventmanagmentapp.entity.User;
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

    public EventDTO getEventById(Long eventId) {

        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event doesn't exist!"));

        return EventDTO
                .builder()
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .description(event.getDescription())
                .link(event.getLink())
                .build();
    }

    public List<EventDTO> getAllEvents() {

        return eventRepository
                .findAll()
                .stream()
                .map((event) -> new EventDTO(
                        event.getId(),
                        event.getName(),
                        event.getStartDate(),
                        event.getEndDate(),
                        event.getDescription(),
                        event.getLink()))
                .toList();
    }
    public EventDTO createEvent(EventDTO eventDTO, Long userId) {
        // TODO: Add user to event
        User user = userService.getUserWithAllFields(userId);

        Event event = Event
                .builder()
                .id(userId)
                .name(eventDTO.getName())
                .startDate(eventDTO.getStartDate())
                .endDate(eventDTO.getEndDate())
                .description(eventDTO.getDescription())
                .link(eventDTO.getLink())
                .build();

        Event createdEvent = eventRepository.save(event);
        user.getEvents().add(event);
        userService.updateUser(user);

        return eventDTO;
    }
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }
    public EventDTO updateEvent(EventDTO modifiedEvent, Long userId) {
        User user = userService.getUserWithAllFields(userId);

        Event foundUserCurrentlyExistingEvent = user
                .getEvents()
                .stream()
                .filter(event -> modifiedEvent.getId().equals(event.getId()))
                .findAny()
                .orElseThrow(() ->
                        new EventNotFoundException(
                                String.format(
                                        "Event with id=[%d] has not been found",
                                        modifiedEvent.getId()
                                )
                        )
                );

        user.getEvents().remove(foundUserCurrentlyExistingEvent);

        Event updatedEvent = Event
                .builder()
                .id(modifiedEvent.getId())
                .name(modifiedEvent.getName())
                .startDate(modifiedEvent.getStartDate())
                .endDate(modifiedEvent.getEndDate())
                .description(modifiedEvent.getDescription())
                .link(modifiedEvent.getLink())
                .build();

        user.getEvents().add(updatedEvent);

        userService.updateUser(user);

        return modifiedEvent;
    }
}
