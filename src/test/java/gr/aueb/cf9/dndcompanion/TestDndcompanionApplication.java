package gr.aueb.cf9.dndcompanion;

import org.springframework.boot.SpringApplication;

public class TestDndcompanionApplication {

	public static void main(String[] args) {
		SpringApplication.from(DndcompanionApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
