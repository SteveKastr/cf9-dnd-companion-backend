package gr.aueb.cf9.dndcompanion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DndcompanionApplicationTests {

	@Test
	void contextLoads() {
	}

}
