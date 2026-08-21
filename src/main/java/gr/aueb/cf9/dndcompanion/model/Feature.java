package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a specific class/subclass feature (e.g. Rage, Sneak Attack).
 * Referenced by Level entries to indicate what's gained at each level.
 */

@Document(collection = "features")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feature {

    @Id
    private String id;

    private String index;
    private String name;
    private int level;
    @JsonProperty("class")
    private ApiReference characterClass;
    private ApiReference subclass;
    private List<String> desc;
    private List<Prerequisite> prerequisites;
    private ApiReference parent;
    private String reference;
    private Object featureSpecific; // ευέλικτο - invocations/expertise_options/κ.λπ.
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prerequisite {
        private String type;
        private Integer level;
        private String feature;
        private String spell;
    }
}