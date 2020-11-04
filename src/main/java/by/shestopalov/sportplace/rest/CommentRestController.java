package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import by.shestopalov.sportplace.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@CrossOrigin(value = "*")
@RestController
public class CommentRestController {
    private final CommentServiceImpl commentService;

    @Autowired
    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all comments by event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments founded",
                        content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comments not founded"),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
    @GetMapping(value = "/rest/api/v1/comments/{eventId}")
    public ResponseEntity<Collection<Comment>> getAllComments(@PathVariable("eventId") Long eventId){
        return new ResponseEntity<>(commentService.getAllCommentsByEventId(eventId).get(), HttpStatus.OK);
    }

    @Operation(summary = "Add comment to event", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments added",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Wrong format")
    })
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
