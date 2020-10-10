package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    Optional<Event> findEventByName(String name);
    Collection<Event> findAll();
    @Query("SELECT e from EVENTS e where (?1 is null or e.place.country = ?1) and (?2 is null or e.place.name = ?2) and (?3 is null or e.startDate > ?3) and (?4 is null or e.finishDate < ?4)")
    Optional<Collection<Event>> getEventsByParams(String country, String place, Date startDate, Date finishDate);
}
