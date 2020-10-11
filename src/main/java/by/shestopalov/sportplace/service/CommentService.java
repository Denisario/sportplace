package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface CommentService {
    Collection<Comment> getAllComments();
    Optional<Collection<Comment>> getAllCommentsByEventId(Long id);
    void addComment(CommentDto commentDto,
                    Collection<MultipartFile> files,
                    HttpServletRequest req) throws Exception;

    void deleteCommentsByEventId(Long eventId);
}
