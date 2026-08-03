package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Subrace;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubraceRepository extends MongoRepository<Subrace, String> {
    Optional<Subrace> findByIndex(String index);
    List<Subrace> findByRaceName(String raceName);
}