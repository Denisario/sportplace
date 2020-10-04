package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
