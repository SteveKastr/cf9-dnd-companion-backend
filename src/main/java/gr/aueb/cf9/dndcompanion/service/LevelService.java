package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.model.Level;
import gr.aueb.cf9.dndcompanion.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    public List<Level> getLevelsByClassName(String className) {
        return levelRepository.findByCharacterClassNameAndSubclassIsNull(className);
    }

    public List<Level> getLevelsBySubclassName(String subclassName) {
        return levelRepository.findBySubclassName(subclassName);
    }
}