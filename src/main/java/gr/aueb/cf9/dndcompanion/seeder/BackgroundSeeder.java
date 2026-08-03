package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Background;
import gr.aueb.cf9.dndcompanion.repository.BackgroundRepository;
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
public class BackgroundSeeder implements CommandLineRunner {

    private final BackgroundRepository backgroundRepository;

    @Override
    public void run(String... args) throws Exception {
        if (backgroundRepository.count() > 0) {
            log.info("Backgrounds already seeded ({} documents) - skipping", backgroundRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Backgrounds.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Background> backgrounds = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Background.class));

            backgroundRepository.saveAll(backgrounds);
            log.info("Seeded {} backgrounds", backgrounds.size());
        }
    }
}