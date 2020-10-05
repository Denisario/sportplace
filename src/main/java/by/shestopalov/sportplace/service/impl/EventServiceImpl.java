package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.repository.EventRepository;
import by.shestopalov.sportplace.repository.PlaceRepository;
import by.shestopalov.sportplace.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public Collection<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> getEventByName(String name) {
        return eventRepository.getEventByName(name);
    }

    @Override
    public void saveEvent(EventDto eventDto) {
        Event event = Mapper.map(eventDto, Event.class);
        event.setPlace(placeRepository
                .getPlaceByName(eventDto
                        .getPlaceName())
                .get());

        event.setComments(new HashSet<>());
        eventRepository.save(event);
    }
}
