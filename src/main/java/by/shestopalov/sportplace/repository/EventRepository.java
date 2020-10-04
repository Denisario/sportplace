package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
