package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.PlaceDto;
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

@Slf4j
@Controller
public class AddPlaceController {
    @GetMapping(value = "/addPlace")
    public ModelAndView getPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPlace");

        model.addAttribute("placeDto", new PlaceDto());

        log.info("/addPlace - GET");
        return modelAndView;
    }

    @PostMapping(value = "/addPlace")
    public ModelAndView addPlace(@Valid @ModelAttribute("placeDto") PlaceDto placeDto,
                                 Errors errors){
        ModelAndView modelAndView = new ModelAndView();
        if(errors.hasErrors()){
            modelAndView.setViewName("addPlace");
            return modelAndView;
        }

        Place place = Mapper.map(placeDto, Place.class);
        place.setId((long)DataCore.places.size()+1);

        place.setEvents(new HashSet<>());
        DataCore.places.add(place);

        modelAndView.setViewName("redirect:/admin");

        log.info("/addPlace - POST");
        return modelAndView;
    }
}
