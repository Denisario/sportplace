package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.data.DataCore;
import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import java.util.Set;

@Controller
@Slf4j
public class CommentsController {
    @Value("${upload.path}")
    private String path;

    @PostMapping(value = "/events/{eventId}")
    public ModelAndView addCommentToEvent(@PathVariable("eventId") Long id,
                                          @RequestParam("file") Set<MultipartFile> files,
                                          @Valid @ModelAttribute("commentDto") CommentDto commentDto,
                                          Errors errors,
                                          Model model,
                                          HttpServletRequest req) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = false;

        commentDto.setFilename(new HashSet<>());

        for (var file:files) {
            if(file.getOriginalFilename().equals("")) continue;

            if(!file.getOriginalFilename().matches("^(?:.*\\.(?=(jpg|jpeg|png|bmp)$))?[^.]*$")&&!file.getOriginalFilename().equals("")) {
                flag = true;
                model.addAttribute("fileError", "You try to load no image");
            }
            File fileDir = new File(path);

            if(!fileDir.exists()){
                fileDir.mkdirs();
            }

            String UUID = java.util.UUID.randomUUID().toString();
            String finalPath = UUID +"."+ file.getOriginalFilename();
            file.transferTo(new File(path+ "/"+finalPath));

            commentDto.getFilename().add("../img/"+finalPath);
        }

        if(errors.hasErrors()||flag){
            modelAndView.setViewName("event");
            model.addAttribute("event", DataCore.events
                    .stream()
                    .filter(x->x.getId()
                            .equals(id))
                    .findFirst()
                    .get());
            commentDto.setFilename(new HashSet<>());
            model.addAttribute("commentDto", commentDto);
            return modelAndView;
        }

        flag = false;

        Comment comment = Mapper.map(commentDto, Comment.class);
        comment.setId((long) DataCore.comments.size()+1);

        model.addAttribute("event", DataCore.events.stream().filter(x->x.getId().equals(id)).findFirst().get());

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
        modelAndView.setViewName("event");

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
