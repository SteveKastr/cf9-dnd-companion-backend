package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Level;
import gr.aueb.cf9.dndcompanion.repository.LevelRepository;
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
public class LevelSeeder implements CommandLineRunner {

    private final LevelRepository levelRepository;

    @Override
    public void run(String... args) throws Exception {
        if (levelRepository.count() > 0) {
            log.info("Levels already seeded ({} documents) - skipping", levelRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Levels.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Level> levels = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Level.class));

            levelRepository.saveAll(levels);
            log.info("Seeded {} levels", levels.size());
        }
    }
}