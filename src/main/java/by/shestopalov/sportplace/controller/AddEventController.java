package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.dto.EventDto;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import by.shestopalov.sportplace.service.impl.PlaceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class AddEventController {
    private final EventServiceImpl eventService;
    private final PlaceServiceImpl placeService;

    @Autowired
    public AddEventController(EventServiceImpl eventService,
                              PlaceServiceImpl placeService) {
        this.eventService = eventService;
        this.placeService = placeService;
    }

    @GetMapping(value = "/addEvent")
    public ModelAndView getPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addEvent");

        model.addAttribute("eventDto", new EventDto());
        model.addAttribute("eventPlaces", placeService.convertToNames());

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
            model.addAttribute("eventPlaces", placeService.convertToNames());
            return modelAndView;
        }

        eventService.saveEvent(eventDto);

        modelAndView.setViewName("redirect:/events");

        log.info("/addEvent - POST");
        return modelAndView;
    }
}
