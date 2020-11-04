package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get events size")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Size returned",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = "/rest/api/v1/events/length")
    public ResponseEntity<Integer> getAllEventsByInput(){
        return new ResponseEntity<>(eventService.getAllEvents().size(), HttpStatus.OK);
    }

    @Operation(summary = "Find event by different params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events founded",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Events not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
    @GetMapping(value = "/rest/api/v1/events/input")
    public ResponseEntity<Collection<Event>> getAllEventsByInput(@RequestParam("input") String input, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return new ResponseEntity<>(eventService.getAllEventByName(input, page, size), HttpStatus.OK);
    }

    @Operation(summary = "Get event by event id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event founded",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Event not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
    @GetMapping(value = "/rest/api/v1/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get events with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events founded",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Events not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
    @GetMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Collection<Event>> getNumberOfEvents(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return new ResponseEntity<>(eventService.getEvents(page, size),HttpStatus.OK);
    }

    @Operation(summary = "Add event", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event added",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Wrong format"),
            @ApiResponse(responseCode = "403", description = "Resource forbidden")
    })
    @PostMapping(value = "/rest/api/v1/events")
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid EventDto eventDto){
        eventService.saveEvent(eventDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get events by different params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events founded",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Events not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
    @GetMapping(value = "/rest/api/v1/events/params")
    public ResponseEntity<Collection<Event>> getAllEventsByParams(@RequestParam(value = "country", required = false)  String country,
                                                                  @RequestParam(value = "place", required = false)  String place,
                                                                  @RequestParam(value = "startDate", required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                  @RequestParam(value = "finishDate", required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date finishDate){
        return new ResponseEntity<>(eventService.getAllEventByParams(new EventDto(country, startDate, finishDate, place)), HttpStatus.OK);
    }

    @Operation(summary = "Delete event", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Event not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format"),
            @ApiResponse(responseCode = "403", description = "Resource forbidden")
    })
    @DeleteMapping(value = "/rest/api/v1/events/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update event", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Event not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format"),
            @ApiResponse(responseCode = "403", description = "Resource forbidden")
    })
    @PutMapping(value = "/rest/api/v1/events/{id}")
    public ResponseEntity<Event> updatePlace(@PathVariable("id") Long id, @RequestBody EventDto eventDto){
        eventService.updateEvent(eventDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
