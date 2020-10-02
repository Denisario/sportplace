package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.data.DataCore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
    @GetMapping(value = "/admin")
    public ModelAndView getPage(@CookieValue("username") String username){
        ModelAndView modelAndView = new ModelAndView();
        if(isAdmin(username)){
            modelAndView.setViewName("adminPage");
        }else{
           modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    private boolean isAdmin(String username){
        return DataCore.users.stream()
                .filter(x->x.getUsername().equals(username))
                .filter(x->x.getRole().getName().equals("ADMIN")).findFirst().isPresent();
    }
}
