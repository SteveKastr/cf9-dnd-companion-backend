package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Rule;
import gr.aueb.cf9.dndcompanion.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @GetMapping
    public List<Rule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/{index}")
    public Rule getRuleByIndex(@PathVariable String index) {
        return ruleService.getRuleByIndex(index);
    }
}