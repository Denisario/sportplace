package by.shestopalov.sportplace.service;


import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;

import java.util.Collection;

public interface PlaceService {
    Collection<Place> getAllPlaces();
    Place getPlaceById(Long id);
    Place getPlaceByName(String name);
    void savePlace(PlaceDto placeDto);
    Collection<String> convertToNames();
}
