package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Background;
import gr.aueb.cf9.dndcompanion.service.BackgroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/backgrounds")
@RequiredArgsConstructor
public class BackgroundController {

    private final BackgroundService backgroundService;

    @GetMapping
    public List<Background> getAllBackgrounds() {
        return backgroundService.getAllBackgrounds();
    }

    @GetMapping("/{index}")
    public Background getBackgroundByIndex(@PathVariable String index) {
        return backgroundService.getBackgroundByIndex(index);
    }
}