package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.PlaceDto;
import by.shestopalov.sportplace.entity.Place;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddPlaceController {
    @GetMapping(value = "/addPlace")
    public ModelAndView getPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addPlace");
        model.addAttribute("place", new Place());
        return modelAndView;
    }

    @PostMapping(value = "/addPlace")
    public ModelAndView addPlace(@ModelAttribute("place") PlaceDto placeDto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        Place place = Mapper.map(placeDto, Place.class);
        place.setId((long)DataCore.places.size()+1);
        DataCore.places.add(place);
        System.out.println(DataCore.places);
        return modelAndView;
    }
}
