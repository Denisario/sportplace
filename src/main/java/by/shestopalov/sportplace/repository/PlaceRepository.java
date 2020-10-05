package by.shestopalov.sportplace.repository;

import by.shestopalov.sportplace.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> getPlaceByName(String name);
}
