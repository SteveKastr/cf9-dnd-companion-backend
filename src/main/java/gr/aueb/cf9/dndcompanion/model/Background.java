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
    private Object languageOptions; // ευέλικτο - Choice schema
    private List<StartingEquipmentEntry> startingEquipment;
    private List<Object> startingEquipmentOptions; // ευέλικτο - Choice schema
    private Cost startingGold; // reuse από model/common
    private Feature feature;
    private Object personalityTraits; // ευέλικτο - Choice schema
    private Object ideals;            // ευέλικτο - Choice schema
    private Object bonds;             // ευέλικτο - Choice schema
    private Object flaws;             // ευέλικτο - Choice schema
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