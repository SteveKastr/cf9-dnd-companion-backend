package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Trait;
import gr.aueb.cf9.dndcompanion.service.TraitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/traits")
@RequiredArgsConstructor
public class TraitController {

    private final TraitService traitService;

    @GetMapping("/{index}")
    public Trait getTraitByIndex(@PathVariable String index) {
        return traitService.getTraitByIndex(index);
    }
}