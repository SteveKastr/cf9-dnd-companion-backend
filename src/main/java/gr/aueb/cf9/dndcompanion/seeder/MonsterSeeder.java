package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Monster;
import gr.aueb.cf9.dndcompanion.repository.MonsterRepository;
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
public class MonsterSeeder implements CommandLineRunner {

    private final MonsterRepository monsterRepository;

    @Override
    public void run(String... args) throws Exception {
        if (monsterRepository.count() > 0) {
            log.info("Monsters already seeded ({} documents) - skipping", monsterRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Monsters.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Monster> monsters = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Monster.class));

            monsterRepository.saveAll(monsters);
            log.info("Seeded {} monsters", monsters.size());
        }
    }
}