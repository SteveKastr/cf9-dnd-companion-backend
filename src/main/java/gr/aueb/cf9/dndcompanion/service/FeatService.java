package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Feat;
import gr.aueb.cf9.dndcompanion.repository.FeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatService {

    private final FeatRepository featRepository;

    public List<Feat> getAllFeats() {
        return featRepository.findAll();
    }

    public Feat getFeatByIndex(String index) {
        return featRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Feat not found: " + index));
    }
}