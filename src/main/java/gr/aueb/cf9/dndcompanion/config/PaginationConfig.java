package gr.aueb.cf9.dndcompanion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

/**
 * Ensures paginated API responses (Page<T>) are serialized using Spring Data's
 * stable PagedModel format instead of the internal, unstable PageImpl structure.
 * Without this, Spring emits a warning about JSON structure stability.
 */

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class PaginationConfig {
}