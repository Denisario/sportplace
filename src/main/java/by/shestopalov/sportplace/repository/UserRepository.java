package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
