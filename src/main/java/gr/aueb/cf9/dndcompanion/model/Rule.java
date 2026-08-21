package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents a top-level rules category (e.g. Combat, Spellcasting).
 * The desc field contains Markdown-formatted introductory text; the
 * full content lives in the referenced RuleSection entries.
 */

@Document(collection = "rules")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    private String id;

    private String index;
    private String name;
    private String desc;
    private List<ApiReference> subsections;
    private String url;
}