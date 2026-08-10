package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Spell;
import gr.aueb.cf9.dndcompanion.service.SpellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spells")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @GetMapping
    public Page<Spell> getAllSpells(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String className) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return spellService.getAllSpells(pageable, level, className);
    }

    @GetMapping("/{index}")
    public Spell getSpellByIndex(@PathVariable String index) {
        return spellService.getSpellByIndex(index);
    }

    @GetMapping("/level/{level}")
    public List<Spell> getSpellsByLevel(@PathVariable int level) {
        return spellService.getSpellsByLevel(level);
    }
}