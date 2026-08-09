package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Trait;
import gr.aueb.cf9.dndcompanion.repository.TraitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraitService {

    private final TraitRepository traitRepository;

    public Trait getTraitByIndex(String index) {
        return traitRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Trait not found: " + index));
    }
}