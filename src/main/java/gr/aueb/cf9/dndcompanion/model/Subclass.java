package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents a subclass (e.g. Berserker Barbarian, Evocation
 * Wizard). The JSON field "class" is mapped to characterClass since
 * "class" is a reserved keyword in Java.
 */

@Document(collection = "subclasses")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subclass {

    @Id
    private String id;

    private String index;
    private String name;
    @JsonProperty("class")
    private ApiReference characterClass;
    private String subclassFlavor;
    private List<String> desc;
    private String subclassLevels; // URL string
    private List<SubclassSpell> spells;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubclassSpell {
        private List<SpellPrerequisite> prerequisites;
        private ApiReference spell;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpellPrerequisite {
        private String index;
        private String type;
        private String name;
        private String url;
    }
}