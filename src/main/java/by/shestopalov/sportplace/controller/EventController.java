package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.data.DataCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class EventController {
    @GetMapping(value = "/events")
    public ModelAndView getAllEvents(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");
        model.addAttribute("events", DataCore.events);
        log.info("/events - GET");
        return modelAndView;
    }

    @GetMapping(value = "/events/{id}")
    public ModelAndView getEventById(@PathVariable("id") Long id, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event");
        model.addAttribute("event", DataCore.events.stream().filter(x->x.getId().equals(id)).findFirst().get());
        log.info("/events/{id} - GET");
        return modelAndView;
    }
}
