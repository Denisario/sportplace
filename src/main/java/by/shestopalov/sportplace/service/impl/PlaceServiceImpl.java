package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.aspect.Loggable;
import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.repository.PlaceRepository;
import by.shestopalov.sportplace.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final EventServiceImpl eventService;
    private final CommentServiceImpl commentService;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, EventServiceImpl eventService, CommentServiceImpl commentService) {
        this.placeRepository = placeRepository;
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @Override
    @Loggable
    public Collection<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    @Loggable
    public Place getPlaceById(Long id) {
        return placeRepository.findById(id).get();
    }

    @Override
    @Loggable
    public Place getPlaceByName(String name) {
        return placeRepository
                .findPlaceByName(name)
                .get();
    }

    @Override
    @Loggable
    public void savePlace(PlaceDto placeDto) {
        Place place = Mapper.map(placeDto, Place.class);

        place.setEvents(new HashSet<>());

        placeRepository.save(place);
    }

    @Override
    public Collection<String> convertToNames() {
        return getAllPlaces()
                .stream()
                .map(Place::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<String> getAllCountries() {
        return placeRepository.getAllCountries();
    }

    @Override
    public Collection<String> getAllPlaceNameByCountryName(String name) {
        return placeRepository.getAllPlaceNamesByCountry(name).get();
    }

    @Override
    public void deletePlace(Long id) {
        if(eventService.getAllEventsByPlaceId(id).isPresent()){
            for (var x:eventService.getAllEventsByPlaceId(id).get()) {
                if(commentService.getAllCommentsByEventId(x.getId()).isPresent()){
                    for(var y: commentService.getAllCommentsByEventId(x.getId()).get()){
                        commentService.deleteCommentsByEventId(y.getId());
                    }
                }

                eventService.deleteAllEventsByPlaceId(id);
            }
        }
        placeRepository.deletePlaceById(id);
    }

    @Override
    public Place updatePlace(PlaceDto place, Long placeId) {
        Place placeToUpdate=placeRepository.findById(placeId).get();
        placeToUpdate.setId(place.getId());
        placeToUpdate.setCity(place.getCity());
        placeToUpdate.setName(place.getName());
        placeToUpdate.setCountry(place.getCountry());
        placeToUpdate.setNumber(place.getNumber());
        placeToUpdate.setStreet(place.getStreet());
        return placeRepository.save(placeToUpdate);
    }
}
