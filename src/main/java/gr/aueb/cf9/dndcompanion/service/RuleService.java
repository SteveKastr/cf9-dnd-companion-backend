package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Rule;
import gr.aueb.cf9.dndcompanion.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public List<Rule> getAllRules() {
        return ruleRepository.findAllByOrderByNameAsc();
    }

    public Rule getRuleByIndex(String index) {
        return ruleRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Rule not found: " + index));
    }
}