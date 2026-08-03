package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Subclass;
import gr.aueb.cf9.dndcompanion.repository.SubclassRepository;
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
public class SubclassSeeder implements CommandLineRunner {

    private final SubclassRepository subclassRepository;

    @Override
    public void run(String... args) throws Exception {
        if (subclassRepository.count() > 0) {
            log.info("Subclasses already seeded ({} documents) - skipping", subclassRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Subclasses.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Subclass> subclasses = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Subclass.class));

            subclassRepository.saveAll(subclasses);
            log.info("Seeded {} subclasses", subclasses.size());
        }
    }
}