package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.RuleSection;
import gr.aueb.cf9.dndcompanion.service.RuleSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rule-sections")
@RequiredArgsConstructor
public class RuleSectionController {

    private final RuleSectionService ruleSectionService;

    @GetMapping("/{index}")
    public RuleSection getRuleSectionByIndex(@PathVariable String index) {
        return ruleSectionService.getRuleSectionByIndex(index);
    }
}