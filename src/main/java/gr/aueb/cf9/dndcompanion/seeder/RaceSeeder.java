package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Race;
import gr.aueb.cf9.dndcompanion.repository.RaceRepository;
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
public class RaceSeeder implements CommandLineRunner {

    private final RaceRepository raceRepository;

    @Override
    public void run(String... args) throws Exception {
        if (raceRepository.count() > 0) {
            log.info("Races already seeded ({} documents) - skipping", raceRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Races.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Race> races = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Race.class));

            raceRepository.saveAll(races);
            log.info("Seeded {} races", races.size());
        }
    }
}