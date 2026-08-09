package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Level;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LevelRepository extends MongoRepository<Level, String> {
    List<Level> findByCharacterClassNameAndSubclassIsNull(String className);
}