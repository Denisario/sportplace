package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Controller
public class LoginController {
    static {

    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage(Model model){
        log.info("/login - GET");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        model.addAttribute("user", new User());

        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@ModelAttribute("user") UserDto userDto, Model model, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();

        try{
            User user = Mapper.map(userDto, User.class);

            user.setUsername(user.getUsername().toLowerCase());

            model.addAttribute("user", user);

            Optional<User> possibleUser = login(user.getUsername(), user.getPassword());

            if(possibleUser.isPresent()){
                Cookie cookie = new Cookie("username", possibleUser.get().getUsername());
                response.addCookie(cookie);

                log.info("/login - POST");
                modelAndView.setViewName("events");
            }
        } catch (Exception e) {
            modelAndView.setViewName("error");
            model.addAttribute("error", e.getMessage());
        }

        return modelAndView;
    }

    private Optional<User> login(String username, String password) throws UserNameNotFoundException, IncorrectPasswordException {
        User user = DataCore.users
                .stream()
                .filter(x->x.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new UserNameNotFoundException("User not found"));

        if(!user.getPassword().equals(password)) throw new IncorrectPasswordException("Incorrect password");
        return Optional.of(user);
    }
}
