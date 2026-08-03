package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Spell;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SpellRepository extends MongoRepository<Spell, String> {
    Optional<Spell> findByIndex(String index);
    List<Spell> findByLevel(int level);
    List<Spell> findBySchoolName(String schoolName);
}