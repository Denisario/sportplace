package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public ModelAndView getPage(@CookieValue("username") String username){
        ModelAndView modelAndView = new ModelAndView();

        if(userService.isAdmin(username)){
            modelAndView.setViewName("adminPage");
        }else{
           modelAndView.setViewName("error");
        }

        log.info("/admin - GET");
        return modelAndView;
    }
}