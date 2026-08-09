package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Subrace;
import gr.aueb.cf9.dndcompanion.service.SubraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subraces")
@RequiredArgsConstructor
public class SubraceController {

    private final SubraceService subraceService;

    @GetMapping("/{index}")
    public Subrace getSubraceByIndex(@PathVariable String index) {
        return subraceService.getSubraceByIndex(index);
    }
}