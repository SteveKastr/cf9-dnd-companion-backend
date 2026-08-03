package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Race;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RaceRepository extends MongoRepository<Race, String> {
    Optional<Race> findByIndex(String index);
}