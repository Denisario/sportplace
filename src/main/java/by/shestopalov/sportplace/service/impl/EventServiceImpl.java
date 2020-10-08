package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.aspect.Loggable;
import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.repository.EventRepository;
import by.shestopalov.sportplace.repository.PlaceRepository;
import by.shestopalov.sportplace.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

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
    @Loggable
    public Collection<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    @Loggable
    public Event getEventById(Long id) throws Exception {
        if(eventRepository
                .findById(id)
                .isEmpty()) throw new Exception("Event not found");
        return eventRepository
                .findById(id)
                .get();
    }

    @Override
    @Loggable
    public Event getEventByName(String name) throws Exception {
        if(eventRepository
                .findEventByName(name)
                .isEmpty()) throw new Exception("Event not found");
        return eventRepository
                .findEventByName(name)
                .get();
    }

    @Override
    @Loggable
    public void saveEvent(@Valid EventDto eventDto) {
        Event event = Mapper.map(eventDto, Event.class);
        event.setPlace(placeRepository
                .getPlaceByName(eventDto
                        .getPlaceName())
                .get());

        event.setComments(new HashSet<>());
        eventRepository.save(event);
    }

    @Override
    @Loggable
    public Collection<Event> getEvents(int page, int counter) {
        Pageable pageable = PageRequest.of(page, counter);
        return eventRepository.findAll(pageable).getContent();
    }

    @Override
    @Loggable
    public Collection<Event> getAllEventByName(String name, int page, int counter) {
        Pageable pageable = PageRequest.of(page, counter);
        return eventRepository.findAll(pageable).stream().filter(x->x.getName().contains(name)).collect(Collectors.toList());
    }
}
