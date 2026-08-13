package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.RuleSection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RuleSectionRepository extends MongoRepository<RuleSection, String> {
    Optional<RuleSection> findByIndex(String index);
}