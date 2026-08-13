package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.RuleSection;
import gr.aueb.cf9.dndcompanion.repository.RuleSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleSectionService {

    private final RuleSectionRepository ruleSectionRepository;

    public RuleSection getRuleSectionByIndex(String index) {
        return ruleSectionRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Rule section not found: " + index));
    }
}