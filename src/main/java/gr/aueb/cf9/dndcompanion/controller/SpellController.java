package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Spell;
import gr.aueb.cf9.dndcompanion.service.SpellService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spells")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @GetMapping
    public List<Spell> getAllSpells(@RequestParam(required = false) Integer level) {
        if (level != null) {
            return spellService.getSpellsByLevel(level);
        }
        return spellService.getAllSpells();
    }

    @GetMapping("/{index}")
    public Spell getSpellByIndex(@PathVariable String index) {
        return spellService.getSpellByIndex(index);
    }
}