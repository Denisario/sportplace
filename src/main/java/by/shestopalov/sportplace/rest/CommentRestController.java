package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import by.shestopalov.sportplace.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

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

    @PostMapping(value = "/rest/api/v1/comments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Comment> addComment(@RequestParam("file") Collection<MultipartFile> files,
                                              @RequestParam("rating") Integer rating,
                                              @RequestParam("text") String text,
                                              @RequestParam("eventId") Long id,
                                              HttpServletRequest req) throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setRating(rating);
        commentDto.setText(text);
        commentDto.setEventId(id);
        commentService.addComment(commentDto, files, req);
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
