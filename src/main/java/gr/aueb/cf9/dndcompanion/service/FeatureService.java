package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Feature;
import gr.aueb.cf9.dndcompanion.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;

    public Feature getFeatureByIndex(String index) {
        return featureRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found: " + index));
    }
}