package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Background;
import gr.aueb.cf9.dndcompanion.repository.BackgroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

    public List<Background> getAllBackgrounds() {
        return backgroundRepository.findAll();
    }

    public Background getBackgroundByIndex(String index) {
        return backgroundRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Background not found: " + index));
    }
}