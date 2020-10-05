package by.shestopalov.sportplace.controller;

import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.service.impl.CommentServiceImpl;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;

import java.util.Set;

@Controller
@Slf4j
public class CommentsController {
    @Value("${upload.path}")
    private String path;

    private final CommentServiceImpl commentService;
    private final EventServiceImpl eventService;

    @Autowired
    public CommentsController(CommentServiceImpl commentService,
                              EventServiceImpl eventService) {
        this.commentService = commentService;
        this.eventService = eventService;
    }

    @PostMapping(value = "/events/{eventId}")
    public ModelAndView addCommentToEvent(@PathVariable("eventId") Long id,
                                          @RequestParam("file") Set<MultipartFile> files,
                                          @Valid @ModelAttribute("commentDto") CommentDto commentDto,
                                          Errors errors,
                                          Model model,
                                          HttpServletRequest req) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        if(errors.hasErrors()){
            modelAndView.setViewName("event");
            model.addAttribute("event", eventService.getEventById(Long.
                    parseLong(commentDto
                            .getEventId()))
                    .get());
            model.addAttribute("comments", commentService
                    .getAllCommentsByEventId(Long.
                            parseLong(commentDto.
                                    getEventId())).
                            get());

            commentDto.setFilename(new HashSet<>());

            model.addAttribute("commentDto", commentDto);
            return modelAndView;
        }

        commentService.addComment(commentDto, files, model, req);

        modelAndView.setViewName("event");

        model.addAttribute("event", eventService.getEventById(Long.parseLong(commentDto.getEventId())).get());

        log.info("/comments/{eventId} - POST");
        return modelAndView;
    }
}
