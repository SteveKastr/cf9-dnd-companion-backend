package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Subclass;
import gr.aueb.cf9.dndcompanion.service.SubclassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subclasses")
@RequiredArgsConstructor
public class SubclassController {

    private final SubclassService subclassService;

    @GetMapping("/{index}")
    public Subclass getSubclassByIndex(@PathVariable String index) {
        return subclassService.getSubclassByIndex(index);
    }
}