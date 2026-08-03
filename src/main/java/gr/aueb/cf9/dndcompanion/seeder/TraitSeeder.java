package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Trait;
import gr.aueb.cf9.dndcompanion.repository.TraitRepository;
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
public class TraitSeeder implements CommandLineRunner {

    private final TraitRepository traitRepository;

    @Override
    public void run(String... args) throws Exception {
        if (traitRepository.count() > 0) {
            log.info("Traits already seeded ({} documents) - skipping", traitRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Traits.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Trait> traits = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Trait.class));

            traitRepository.saveAll(traits);
            log.info("Seeded {} traits", traits.size());
        }
    }
}