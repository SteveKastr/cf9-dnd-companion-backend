package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.RuleSection;
import gr.aueb.cf9.dndcompanion.repository.RuleSectionRepository;
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
public class RuleSectionSeeder implements CommandLineRunner {

    private final RuleSectionRepository ruleSectionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (ruleSectionRepository.count() > 0) {
            log.info("Rule sections already seeded ({} documents) - skipping", ruleSectionRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Rule-Sections.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<RuleSection> sections = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, RuleSection.class));

            ruleSectionRepository.saveAll(sections);
            log.info("Seeded {} rule sections", sections.size());
        }
    }
}