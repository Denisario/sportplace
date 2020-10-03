package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CommentsController {
    @PostMapping(value = "/comments/{eventId}")
    public ModelAndView addCommentToEvent(@PathVariable("eventId") Long id,
                                          @ModelAttribute("commentDto")CommentDto commentDto,
                                          HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events");

        Comment comment = Mapper.map(commentDto, Comment.class);
        comment.setId((long)DataCore.comments.size()+1);

        comment.setUser(DataCore.users
                .stream()
                .filter(x->x.getUsername()
                        .equals(getUsernameCookie(req.getCookies())))
                .findFirst()
                .get());

        DataCore.users
                .stream()
                .filter(x->x.getUsername()
                        .equals(getUsernameCookie(req.getCookies())))
                .findFirst()
                .get()
                .getComments()
                .add(comment);

        comment.setEvent(DataCore.events
                .stream()
                .filter(x->x.getId().equals(id))
                .findFirst()
                .get());

        DataCore.events
                .stream()
                .filter(x->x.getId()
                        .equals(id))
                .findFirst()
                .get()
                .getComments()
                .add(comment);


        DataCore.comments.add(comment);

        log.info("/comments/{eventId} - POST");
        return modelAndView;
    }

    private String getUsernameCookie(Cookie[] cookie){
        for(Cookie c:cookie){
            if(c.getName().equals("username")){
                return c.getValue();
            }
        }
        return null;
    }
}