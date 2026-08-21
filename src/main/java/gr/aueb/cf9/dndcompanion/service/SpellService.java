package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Spell;
import gr.aueb.cf9.dndcompanion.repository.SpellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpellService {

    private final SpellRepository spellRepository;

    public Page<Spell> getAllSpells(Pageable pageable, Integer level, String className) {
        if (level != null && className != null) {
            return spellRepository.findByLevelAndClassesName(level, className, pageable);
        }
        if (level != null) {
            return spellRepository.findByLevel(level, pageable);
        }
        if (className != null) {
            return spellRepository.findByClassesName(className, pageable);
        }
        return spellRepository.findAll(pageable);
    }

    public Spell getSpellByIndex(String index) {
        return spellRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + index));
    }

}