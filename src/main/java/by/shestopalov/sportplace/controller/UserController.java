package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.entity.Role;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class UserController {
    static {
        Role userRole =new Role(1L, "USER");
        Role adminRole =new Role(2L, "ADMIN");
        DataCore.users.add(new User(1L, "denisario", "123", userRole, null));
        DataCore.users.add(new User(2L, "denisario", "123", userRole, null));
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
    public ModelAndView login(@ModelAttribute("user") User user, Model model){
        ModelAndView modelAndView = new ModelAndView();
        user.setUsername(user.getUsername().toLowerCase());
        model.addAttribute("user", user);
        log.info("/login - POST");
        try {
            if(login(user.getUsername(), user.getPassword()).isPresent()){
                modelAndView.setViewName("welcome");
            }
        } catch (UserNameNotFoundException e) {
            modelAndView.setViewName("error");
            model.addAttribute("error", e.getMessage());
        } catch (IncorrectPasswordException e) {
            modelAndView.setViewName("error");
            model.addAttribute("error", e.getMessage());
        }

        return modelAndView;
    }

    private Optional<User> login(String username, String password) throws UserNameNotFoundException, IncorrectPasswordException {
        User user = DataCore.users.stream().filter(x->x.getUsername().equals(username)).findFirst().orElseThrow(()->new UserNameNotFoundException("User not found"));
        if(!user.getPassword().equals(password)) throw new IncorrectPasswordException("Incorrect password");
        return Optional.ofNullable(user);
    }
}
