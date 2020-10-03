package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RegisterController {
    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        model.addAttribute("userDto", new UserDto());

        log.info("/register - GET");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@ModelAttribute("user") UserDto userDto, Model model){
        ModelAndView modelAndView = new ModelAndView();

        User user = Mapper.map(userDto, User.class);
        register(user);

        model.addAttribute("userDto", userDto);
        modelAndView.setViewName("login");

        log.info("/register - GET");
        return modelAndView;
    }

    private void register(User user){
        user.setRole(DataCore.roles.
                stream().
                filter((x)->x.getName()
                        .equals("USER"))
                .findFirst()
                .get());

        DataCore.users.add(user);
    }
}
