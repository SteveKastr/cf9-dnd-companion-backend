package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Subrace;
import gr.aueb.cf9.dndcompanion.repository.SubraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubraceService {

    private final SubraceRepository subraceRepository;

    public Subrace getSubraceByIndex(String index) {
        return subraceRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Subrace not found: " + index));
    }
}