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
 * Represents a character feat (e.g. Grappler), with
 * optional ability score prerequisites.
 */

@Document(collection = "feats")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feat {

    @Id
    private String id;

    private String index;
    private String name;
    private List<Prerequisite> prerequisites;
    private List<String> desc;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prerequisite {
        private ApiReference abilityScore;
        private int minimumScore;
    }
}