package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;

import java.util.Collection;
import java.util.Optional;

public interface EventService {
    Collection<Event> getAllEvents();
    Event getEventById(Long id) throws Exception;
    Event getEventByName(String name) throws Exception;
    void saveEvent(EventDto eventDto);
    Collection<Event> getEvents(int page, int counter);
    Collection<Event> getAllEventByName(String name, int page, int counter);
    Collection<Event> getAllEventByParams(EventDto eventDto);
    void deleteAllEventsByPlaceId(Long placeId);
    Optional<Collection<Event>> getAllEventsByPlaceId(Long placeId);
}
