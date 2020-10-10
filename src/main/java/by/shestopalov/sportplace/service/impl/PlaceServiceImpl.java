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

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
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
}
