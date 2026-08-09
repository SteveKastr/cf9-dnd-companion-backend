package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Feature;
import gr.aueb.cf9.dndcompanion.repository.FeatureRepository;
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
public class FeatureSeeder implements CommandLineRunner {

    private final FeatureRepository featureRepository;

    @Override
    public void run(String... args) throws Exception {
        if (featureRepository.count() > 0) {
            log.info("Features already seeded ({} documents) - skipping", featureRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Features.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Feature> features = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Feature.class));

            featureRepository.saveAll(features);
            log.info("Seeded {} features", features.size());
        }
    }
}