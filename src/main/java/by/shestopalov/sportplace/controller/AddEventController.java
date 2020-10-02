package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.entity.Event;
import by.shestopalov.sportplace.entity.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AddEventController {
    @GetMapping(value = "/addEvent")
    public ModelAndView getPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addEvent");
        Set<String> places = new HashSet<>();
        for (var x:DataCore.places) {
            places.add(x.getName());
        }
        model.addAttribute("event", new Event());
        model.addAttribute("eventPlaces", places);
        return modelAndView;
    }

    @PostMapping(value = "/addEvent")
    public ModelAndView addPlace(@ModelAttribute("event") EventDto eventDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        Event event = Mapper.map(eventDto, Event.class);
        event.setId((long) DataCore.events.size()+1);
        Place pl= DataCore.places.stream().filter(x->x.getName().equals(event.getName())).findFirst().get();
        event.setPlace(pl);
        DataCore.events.add(event);
        System.out.println(event);
        return modelAndView;
    }
}
