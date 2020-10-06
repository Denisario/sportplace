package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

@CrossOrigin(value = "*")
@RestController
public class PlaceRestController {
    private final PlaceServiceImpl placeService;

    @Autowired
    public PlaceRestController(PlaceServiceImpl placeService) {
        this.placeService = placeService;
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
}
