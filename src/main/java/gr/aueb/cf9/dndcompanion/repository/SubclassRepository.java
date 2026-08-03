package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Subclass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubclassRepository extends MongoRepository<Subclass, String> {
    Optional<Subclass> findByIndex(String index);
    List<Subclass> findByCharacterClassName(String className);
}