package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.CharacterClass;
import gr.aueb.cf9.dndcompanion.repository.CharacterClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterClassService {

    private final CharacterClassRepository characterClassRepository;

    public List<CharacterClass> getAllClasses() {
        return characterClassRepository.findAll();
    }

    public CharacterClass getClassByIndex(String index) {
        return characterClassRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Class not found: " + index));
    }
}