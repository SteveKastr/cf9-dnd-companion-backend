package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import gr.aueb.cf9.dndcompanion.model.common.Cost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents a character background (e.g. Acolyte).
 *
 * NOTE: the nested Feature class here is unrelated to the top-level
 * Feature entity (used for class/subclass features) — this is simply
 * the background's own bonus feature (e.g. "Shelter of the Faithful").
 */

@Document(collection = "backgrounds")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Background {

    @Id
    private String id;

    private String index;
    private String name;
    private List<ApiReference> startingProficiencies;
    private Object languageOptions;
    private List<StartingEquipmentEntry> startingEquipment;
    private List<Object> startingEquipmentOptions;
    private Cost startingGold; // reuse από model/common
    private Feature feature;
    private Object personalityTraits;
    private Object ideals;
    private Object bonds;
    private Object flaws;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StartingEquipmentEntry {
        private ApiReference equipment;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Feature {
        private String name;
        private List<String> desc;
    }
}