package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class EventController {
    private final EventServiceImpl eventService;

    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/events")
    public ModelAndView getAllEvents(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");

        model.addAttribute("events", eventService.getAllEvents());

        log.info("/events - GET");
        return modelAndView;
    }

    @GetMapping(value = "/events/{id}")
    public ModelAndView getEventById(@PathVariable("id") Long id,
                                     Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event");

        model.addAttribute("commentDto", new CommentDto());
        model.addAttribute("event", eventService.getEventById(id).get());

        log.info("/events/{id} - GET");
        return modelAndView;
    }
}
