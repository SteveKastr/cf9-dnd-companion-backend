package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.CharacterClass;
import gr.aueb.cf9.dndcompanion.service.CharacterClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class CharacterClassController {

    private final CharacterClassService characterClassService;

    @GetMapping
    public List<CharacterClass> getAllClasses() {
        return characterClassService.getAllClasses();
    }

    @GetMapping("/{index}")
    public CharacterClass getClassByIndex(@PathVariable String index) {
        return characterClassService.getClassByIndex(index);
    }
}