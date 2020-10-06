package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(value = "*")
@RestController
public class EventRestController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventRestController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Collection<Event>> getAllEvents(){
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/api/v1/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid EventDto eventDto){
        eventService.saveEvent(eventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
