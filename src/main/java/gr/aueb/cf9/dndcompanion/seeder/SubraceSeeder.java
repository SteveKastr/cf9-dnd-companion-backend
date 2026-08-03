package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Subrace;
import gr.aueb.cf9.dndcompanion.repository.SubraceRepository;
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
public class SubraceSeeder implements CommandLineRunner {

    private final SubraceRepository subraceRepository;

    @Override
    public void run(String... args) throws Exception {
        if (subraceRepository.count() > 0) {
            log.info("Subraces already seeded ({} documents) - skipping", subraceRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Subraces.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Subrace> subraces = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Subrace.class));

            subraceRepository.saveAll(subraces);
            log.info("Seeded {} subraces", subraces.size());
        }
    }
}