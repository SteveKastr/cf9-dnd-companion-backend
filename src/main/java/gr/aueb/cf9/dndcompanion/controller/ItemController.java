package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Item;
import gr.aueb.cf9.dndcompanion.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(Authentication authentication) {
        return itemService.getAllItems(authentication);
    }

    @GetMapping("/{index}")
    public Item getItemByIndex(@PathVariable String index, Authentication authentication) {
        return itemService.getItemByIndex(index, authentication);
    }
}