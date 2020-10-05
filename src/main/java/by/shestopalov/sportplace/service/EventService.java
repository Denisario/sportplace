package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;

import java.util.Collection;
import java.util.Optional;

public interface EventService {
    Collection<Event> getAllEvents();
    Optional<Event> getEventById(Long id);
    Optional<Event> getEventByName(String name);
    void saveEvent(EventDto eventDto);
}
