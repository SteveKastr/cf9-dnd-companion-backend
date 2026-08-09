package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends MongoRepository<Feature, String> {
    Optional<Feature> findByIndex(String index);
    List<Feature> findByCharacterClassName(String className);
}