package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Feat;
import gr.aueb.cf9.dndcompanion.service.FeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feats")
@RequiredArgsConstructor
public class FeatController {

    private final FeatService featService;

    @GetMapping
    public List<Feat> getAllFeats() {
        return featService.getAllFeats();
    }

    @GetMapping("/{index}")
    public Feat getFeatByIndex(@PathVariable String index) {
        return featService.getFeatByIndex(index);
    }
}