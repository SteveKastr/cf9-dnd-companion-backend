package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Monster;
import gr.aueb.cf9.dndcompanion.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Paginated, filterable Monsters listing (by type, challenge rating,
 * and name search). Restricted to Admin and GameMaster roles — Players
 * cannot access monster stat blocks at all.
 */

@RestController
@RequestMapping("/api/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GAME_MASTER')")
    public Page<Monster> getAllMonsters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double challengeRating,
            @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return monsterService.getAllMonsters(pageable, type, challengeRating, search);
    }

    @GetMapping("/{index}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GAME_MASTER')")
    public Monster getMonsterByIndex(@PathVariable String index) {
        return monsterService.getMonsterByIndex(index);
    }
}