package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import by.shestopalov.sportplace.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(value = "*")
@RestController
public class PlaceRestController {
    private final PlaceServiceImpl placeService;
    private final EventServiceImpl eventService;

    @Autowired
    public PlaceRestController(PlaceServiceImpl placeService, EventServiceImpl eventService) {
        this.placeService = placeService;
        this.eventService = eventService;
    }

    @GetMapping(value = "/rest/api/v1/places/names")
    public ResponseEntity<Collection<String>> getAllPlaceNames(){
        return new ResponseEntity<>(placeService.convertToNames(), HttpStatus.OK);
    }

    @PostMapping(value = "/rest/api/v1/places")
    public ResponseEntity<PlaceDto> savePlace(@RequestBody @Valid PlaceDto placeDto){
        placeService.savePlace(placeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/places/countries")
    public ResponseEntity<Collection<String>> getAllCountries(){
        return new ResponseEntity<>(placeService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/places/placeNames")
    public ResponseEntity<Collection<String>> getAllPlaceNamesByCountry(@RequestParam("countryName") String countryName){
        return new ResponseEntity<>(placeService.getAllPlaceNameByCountryName(countryName), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/places")
    public ResponseEntity<Collection<Place>> getAllPlaces(){
        return new ResponseEntity<>(placeService.getAllPlaces(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/rest/api/v1/places/{id}")
    public ResponseEntity<Place> deletePlace(@PathVariable("id") Long id){
        placeService.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/places/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") Long id){
        return new ResponseEntity<>(placeService.getPlaceById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/rest/api/v1/places/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable("id") Long id, @RequestBody PlaceDto place){
        return new ResponseEntity<>(placeService.updatePlace(place, id), HttpStatus.OK);
    }
}
