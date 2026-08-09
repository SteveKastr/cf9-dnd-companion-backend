package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Subclass;
import gr.aueb.cf9.dndcompanion.repository.SubclassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubclassService {

    private final SubclassRepository subclassRepository;

    public Subclass getSubclassByIndex(String index) {
        return subclassRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Subclass not found: " + index));
    }
}