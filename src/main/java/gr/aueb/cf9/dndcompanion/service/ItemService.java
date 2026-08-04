package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.Item;
import gr.aueb.cf9.dndcompanion.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private static final String MAGIC = "magic";

    private final ItemRepository itemRepository;

    public Page<Item> getAllItems(Pageable pageable, Authentication authentication) {
        if (isPlayer(authentication)) {
            return itemRepository.findByItemTypeNot(MAGIC, pageable);
        }
        return itemRepository.findAll(pageable);
    }

    public Item getItemByIndex(String index, Authentication authentication) {
        Item item = itemRepository.findByIndex(index)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + index));

        if (isPlayer(authentication) && MAGIC.equals(item.getItemType())) {
            throw new EntityNotFoundException("Item not found: " + index);
        }

        return item;
    }

    private boolean isPlayer(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_PLAYER"));
    }
}