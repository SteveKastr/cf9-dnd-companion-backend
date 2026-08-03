package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.CharacterClass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterClassRepository extends MongoRepository<CharacterClass, String> {
    Optional<CharacterClass> findByIndex(String index);
}