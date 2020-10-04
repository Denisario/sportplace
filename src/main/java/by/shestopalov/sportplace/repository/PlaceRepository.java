package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
