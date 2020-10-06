package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;

import java.util.Collection;

public interface EventService {
    Collection<Event> getAllEvents();
    Event getEventById(Long id) throws Exception;
    Event getEventByName(String name) throws Exception;
    void saveEvent(EventDto eventDto);
}
