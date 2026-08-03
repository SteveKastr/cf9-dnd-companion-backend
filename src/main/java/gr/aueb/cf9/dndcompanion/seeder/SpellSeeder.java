package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Spell;
import gr.aueb.cf9.dndcompanion.repository.SpellRepository;
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
public class SpellSeeder implements CommandLineRunner {

    private final SpellRepository spellRepository;

    @Override
    public void run(String... args) throws Exception {
        if (spellRepository.count() > 0) {
            log.info("Spells already seeded ({} documents) - skipping", spellRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Spells.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Spell> spells = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Spell.class));

            spellRepository.saveAll(spells);
            log.info("Seeded {} spells", spells.size());
        }
    }
}