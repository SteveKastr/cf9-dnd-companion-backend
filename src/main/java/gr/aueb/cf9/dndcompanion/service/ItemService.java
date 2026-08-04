package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Item;
import gr.aueb.cf9.dndcompanion.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private static final String MAGIC = "magic";

    private final ItemRepository itemRepository;

    public List<Item> getAllItems(Authentication authentication) {
        List<Item> items = itemRepository.findAll();

        if (isPlayer(authentication)) {
            return items.stream()
                    .filter(item -> !MAGIC.equals(item.getItemType()))
                    .toList();
        }

        return items;
    }

    public Item getItemByIndex(String index, Authentication authentication) {
        Item item = itemRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + index));

        if (isPlayer(authentication) && MAGIC.equals(item.getItemType())) {
            throw new EntityNotFoundException("Item not found: " + index); //για να μην γνωρίζει αν υπάρχει ή όχι
        }

        return item;
    }

    private boolean isPlayer(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_PLAYER"));
    }
}