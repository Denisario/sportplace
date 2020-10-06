package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.entity.Comment;
import by.shestopalov.sportplace.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(value = "*")
@RestController
public class CommentRestController {
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/rest/api/v1/comments/{eventId}")
    public ResponseEntity<Collection<Comment>> getAllComments(@PathVariable("eventId") Long eventId){
        return new ResponseEntity<>(commentService.getAllCommentsByEventId(eventId), HttpStatus.OK);
    }
}
