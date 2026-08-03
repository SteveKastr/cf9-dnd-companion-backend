package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Race;
import gr.aueb.cf9.dndcompanion.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Race getRaceByIndex(String index) {
        return raceRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + index));
    }
}