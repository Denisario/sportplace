package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> getEventByName(String name);
}
