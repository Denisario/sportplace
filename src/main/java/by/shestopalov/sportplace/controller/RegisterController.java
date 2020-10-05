package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.service.impl.UserServiceImpl;
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
public class RegisterController {
    private final UserServiceImpl userService;

    @Autowired
    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        model.addAttribute("userDto", new UserDto());

        log.info("/register - GET");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@ModelAttribute("userDto") @Valid UserDto userDto,
                                 Errors errors,
                                 Model model){
        ModelAndView modelAndView = new ModelAndView();

        try {
            if(errors.hasErrors()){
                modelAndView.setViewName("register");
                return modelAndView;
            }

            userService.register(userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getRepeatPassword());

            model.addAttribute("userDto", userDto);
            modelAndView.setViewName("redirect:/login");

            log.info("/register - POST");
        } catch (Exception e) {
            modelAndView.setViewName("error");
            model.addAttribute("error", e.getMessage());
            log.info("/error - GET");
        }

        return modelAndView;
    }
}
