package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Background;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BackgroundRepository extends MongoRepository<Background, String> {
    Optional<Background> findByIndex(String index);
}