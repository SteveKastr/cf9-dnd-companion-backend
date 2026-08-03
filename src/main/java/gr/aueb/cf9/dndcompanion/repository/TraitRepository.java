package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Trait;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TraitRepository extends MongoRepository<Trait, String> {
    Optional<Trait> findByIndex(String index);
}