package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.CommentDto;
import by.shestopalov.sportplace.entity.Comment;
import by.shestopalov.sportplace.repository.CommentRepository;
import by.shestopalov.sportplace.repository.FileRepository;
import by.shestopalov.sportplace.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
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
    public Collection<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Collection<Comment>> getAllCommentsByEventId(Long id) {
        return commentRepository.getCommentByEventId(id);
    }

    @Override
    public void addComment(CommentDto commentDto,
                           Set<MultipartFile> files,
                           Model model,
                           HttpServletRequest req) throws IOException {
        commentDto.setFilename(new HashSet<>());

        for (var file : files) {
            if (file.getOriginalFilename().equals("")) continue;

            if (!file.getOriginalFilename().matches("^(?:.*\\.(?=(jpg|jpeg|png|bmp)$))?[^.]*$") && !file.getOriginalFilename().equals("")) {
                model.addAttribute("fileError", "You try to load no image");
            }

            java.io.File fileDir = new java.io.File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
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

        comment.setEvent(eventService.getEventById(Long.parseLong(commentDto.getEventId())).get());
        comment.setUser(userService.getUserByUsername(getUsernameCookie(req.getCookies())).get());

        commentRepository.save(comment);
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
