package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Item;
import gr.aueb.cf9.dndcompanion.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ItemSeeder implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {

        if (itemRepository.count() > 0) {
            log.info("Items collection already has data ({} documents) - skipping seed",
                    itemRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        int mundaneCount = seedFromFile(mapper, "seed/5e-SRD-Equipment.json", "mundane");
        int magicCount = seedFromFile(mapper, "seed/5e-SRD-Magic-Items.json", "magic");

        log.info("Seeded {} mundane items and {} magic items", mundaneCount, magicCount);
    }

    private int seedFromFile(ObjectMapper mapper, String path, String itemType) throws Exception {

        Resource resource = new ClassPathResource(path);

        try (InputStream inputStream = resource.getInputStream()) {
            List<Item> items = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Item.class));

            items.forEach(item -> item.setItemType(itemType));

            itemRepository.saveAll(items);

            return items.size();
        }
    }
}