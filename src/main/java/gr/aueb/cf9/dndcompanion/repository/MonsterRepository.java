package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MonsterRepository extends MongoRepository<Monster, String> {
    Optional<Monster> findByIndex(String index);
}