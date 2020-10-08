package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    Optional<Event> findEventByName(String name);
    Collection<Event> findAll();
}
