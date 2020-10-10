package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

@CrossOrigin(value = "*")
@RestController
public class EventRestController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventRestController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/rest/api/v1/events/length")
    public ResponseEntity<Integer> getAllEventsByInput(){
        return new ResponseEntity<>(eventService.getAllEvents().size(), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/events/input")
    public ResponseEntity<Collection<Event>> getAllEventsByInput(@RequestParam("input") String input, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return new ResponseEntity<>(eventService.getAllEventByName(input, page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Collection<Event>> getNumberOfEvents(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return new ResponseEntity<>(eventService.getEvents(page, size),HttpStatus.OK);
    }

    @PostMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid EventDto eventDto){
        eventService.saveEvent(eventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/events/params")
    public ResponseEntity<Collection<Event>> getAllEventsByParams(@RequestParam(value = "country", required = false)  String country,
                                                                  @RequestParam(value = "place", required = false)  String place,
                                                                  @RequestParam(value = "startDate", required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                  @RequestParam(value = "finishDate", required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finishDate){
        return new ResponseEntity<>(eventService.getAllEventByParams(new EventDto(country, startDate, finishDate, place)), HttpStatus.OK);
    }
}
