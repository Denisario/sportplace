package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.entity.Place;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class AddEventController {
    @GetMapping(value = "/addEvent")
    public ModelAndView getPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addEvent");

        model.addAttribute("eventDto", new EventDto());
        model.addAttribute("eventPlaces", DataCore.places
                .stream()
                .map(Place::getName)
                .collect(Collectors.toSet()));

        log.info("/addEvent - GET");
        return modelAndView;
    }

    @PostMapping(value = "/addEvent")
    public ModelAndView addPlace( @ModelAttribute("eventDto") @Valid EventDto eventDto,
                                  Errors errors,
                                  Model model){
        ModelAndView modelAndView = new ModelAndView();
        if(errors.hasErrors()){
            modelAndView.setViewName("addEvent");
            model.addAttribute("eventPlaces", DataCore.places
                    .stream()
                    .map(Place::getName)
                    .collect(Collectors.toSet()));
            return modelAndView;
        }

        Event event = Mapper.map(eventDto, Event.class);
        event.setId((long) DataCore.events.size()+1);

        event.setPlace(DataCore.places
                .stream()
                .filter(x->x.getName()
                        .equals(eventDto.getPlaceName()))
                .findFirst()
                .get());

        DataCore.places
                .stream()
                .filter(x->x.getName()
                        .equals(eventDto.getPlaceName()))
                .findFirst()
                .get()
                .getEvents()
                .add(event);

        event.setComments(new HashSet<>());
        DataCore.events.add(event);

        modelAndView.setViewName("redirect:/events");

        log.info("/addEvent - POST");
        return modelAndView;
    }
}
