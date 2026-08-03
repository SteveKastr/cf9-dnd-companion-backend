package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Feat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeatRepository extends MongoRepository<Feat, String> {
    Optional<Feat> findByIndex(String index);
}