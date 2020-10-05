package by.shestopalov.sportplace.service;


import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;

import java.util.Collection;
import java.util.Optional;

public interface PlaceService {
    Collection<Place> getAllPlaces();
    Optional<Place> getPlaceById(Long id);
    Optional<Place> getPlaceByName(String name);
    void savePlace(PlaceDto placeDto);
    Collection<String> convertToNames();
}
