package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.model.Item;
import gr.aueb.cf9.dndcompanion.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public Page<Item> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String itemType,
            Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return itemService.getAllItems(pageable, category, itemType, authentication);
    }

    @GetMapping("/{index}")
    public Item getItemByIndex(@PathVariable String index, Authentication authentication) {
        return itemService.getItemByIndex(index, authentication);
    }
}