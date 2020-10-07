package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.aspect.Loggable;
import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import by.shestopalov.sportplace.repository.CommentRepository;
import by.shestopalov.sportplace.repository.FileRepository;
import by.shestopalov.sportplace.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${upload.path}")
    private String path;

    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final UserServiceImpl userService;
    private final EventServiceImpl eventService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              FileRepository fileRepository,
                              UserServiceImpl userService,
                              EventServiceImpl eventService) {
        this.commentRepository = commentRepository;
        this.fileRepository = fileRepository;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    @Loggable
    public Collection<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Loggable
    public Collection<Comment> getAllCommentsByEventId(Long id) {
        return commentRepository
                .getCommentByEventId(id)
                .get();
    }

    @Override
    @Loggable
    public void addComment(CommentDto commentDto,
                           Collection<MultipartFile> files,
                           HttpServletRequest req) throws Exception {
        commentDto.setFilename(new HashSet<>());

        for (var file : files) {
            if (file.getOriginalFilename()
                    .equals("")) continue;

            if (!file.getOriginalFilename()
                    .matches("^(?:.*\\.(?=(jpg|jpeg|png|bmp)$))?[^.]*$") && !file.
                    getOriginalFilename()
                    .equals("")) {
            }

            java.io.File fileDir = new java.io.File(path);

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            String UUID = java.util.UUID.randomUUID().toString();
            String finalPath = UUID + "." + file.getOriginalFilename();
            file.transferTo(new java.io.File(path + "/" + finalPath));

            by.shestopalov.sportplace.entity.File commentImg = new by.shestopalov.sportplace.entity.File();

            commentImg.setComment(new Comment());
            commentImg.setFilename("../img/" + finalPath);
            commentDto.getFilename().add(commentImg);
        }

        Comment comment = Mapper.map(commentDto, Comment.class);
        comment.setFile(new HashSet<>());

        for (var x : commentDto.getFilename()) {
            comment.getFile().add(x);
            x.setComment(comment);
        }

        comment.setEvent(eventService
                .getEventById(commentDto
                        .getEventId()));
        comment.setUser(userService
                .getUserByUsername("denisario"));



        commentRepository.save(comment);
    }
}
