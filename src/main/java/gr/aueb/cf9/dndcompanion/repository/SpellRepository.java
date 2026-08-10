package gr.aueb.cf9.dndcompanion.repository;

import gr.aueb.cf9.dndcompanion.model.Spell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SpellRepository extends MongoRepository<Spell, String> {
    Optional<Spell> findByIndex(String index);
    List<Spell> findByLevel(int level);
    List<Spell> findBySchoolName(String schoolName);

    Page<Spell> findByLevel(int level, Pageable pageable);
    Page<Spell> findByClassesName(String className, Pageable pageable);
    Page<Spell> findByLevelAndClassesName(int level, String className, Pageable pageable);
}