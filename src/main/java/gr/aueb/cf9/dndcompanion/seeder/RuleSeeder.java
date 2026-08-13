package gr.aueb.cf9.dndcompanion.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import gr.aueb.cf9.dndcompanion.model.Rule;
import gr.aueb.cf9.dndcompanion.repository.RuleRepository;
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
public class RuleSeeder implements CommandLineRunner {

    private final RuleRepository ruleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (ruleRepository.count() > 0) {
            log.info("Rules already seeded ({} documents) - skipping", ruleRepository.count());
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        Resource resource = new ClassPathResource("seed/5e-SRD-Rules.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<Rule> rules = mapper.readValue(inputStream, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Rule.class));

            ruleRepository.saveAll(rules);
            log.info("Seeded {} rules", rules.size());
        }
    }
}