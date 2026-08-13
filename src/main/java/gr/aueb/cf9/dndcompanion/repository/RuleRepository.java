package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends MongoRepository<Rule, String> {
    List<Rule> findAllByOrderByNameAsc();
    Optional<Rule> findByIndex(String index);
}