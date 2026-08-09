package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Level;
import gr.aueb.cf9.dndcompanion.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping
    public List<Level> getLevelsByClass(@RequestParam String className) {
        return levelService.getLevelsByClassName(className);
    }
}