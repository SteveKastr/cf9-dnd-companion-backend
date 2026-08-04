package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Monster;
import gr.aueb.cf9.dndcompanion.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monsters")
@RequiredArgsConstructor
public class MonsterController {

    private final MonsterService monsterService;

//    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'GAME_MASTER')")
//    public List<Monster> getAllMonsters() {
//        return monsterService.getAllMonsters();
//    }

    @GetMapping("/{index}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GAME_MASTER')")
    public Monster getMonsterByIndex(@PathVariable String index) {
        return monsterService.getMonsterByIndex(index);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GAME_MASTER')")
    public Page<Monster> getAllMonsters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return monsterService.getAllMonsters(pageable);
    }
}