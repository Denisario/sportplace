package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.repository.PlaceRepository;
import by.shestopalov.sportplace.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Collection<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Optional<Place> getPlaceByName(String name) {
        return placeRepository.getPlaceByName(name);
    }

    @Override
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
}
