package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.CharacterClass;
import gr.aueb.cf9.dndcompanion.repository.CharacterClassRepository;
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
public class CharacterClassSeeder implements CommandLineRunner {

    private final CharacterClassRepository characterClassRepository;

    @Override
    public void run(String... args) throws Exception {
        if (characterClassRepository.count() > 0) {
            log.info("Classes already seeded ({} documents) - skipping", characterClassRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Classes.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<CharacterClass> classes = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, CharacterClass.class));

            characterClassRepository.saveAll(classes);
            log.info("Seeded {} classes", classes.size());
        }
    }
}