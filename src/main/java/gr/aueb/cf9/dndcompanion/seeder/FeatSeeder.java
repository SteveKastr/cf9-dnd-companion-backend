package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Feat;
import gr.aueb.cf9.dndcompanion.repository.FeatRepository;
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
public class FeatSeeder implements CommandLineRunner {

    private final FeatRepository featRepository;

    @Override
    public void run(String... args) throws Exception {
        if (featRepository.count() > 0) {
            log.info("Feats already seeded ({} documents) - skipping", featRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Feats.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Feat> feats = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Feat.class));

            featRepository.saveAll(feats);
            log.info("Seeded {} feats", feats.size());
        }
    }
}