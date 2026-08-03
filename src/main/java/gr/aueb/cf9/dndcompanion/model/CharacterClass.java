package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "classes")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterClass {

    @Id
    private String id;

    private String index;
    private String name;
    private int hitDie;
    private String classLevels; // URL string, όχι embedded data
    private MultiClassing multiClassing;
    private List<ApiReference> proficiencies;
    private List<Object> proficiencyChoices; // ευέλικτο - Choice schema
    private List<ApiReference> savingThrows;
    private List<StartingEquipment> startingEquipment;
    private List<Object> startingEquipmentOptions; // ευέλικτο - Choice schema
    private List<ApiReference> subclasses;
    private Spellcasting spellcasting;
    private String spells; // URL string
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiClassing {
        private List<Prerequisite> prerequisites;
        private Object prerequisiteOptions; // ευέλικτο - Choice schema
        private List<ApiReference> proficiencies;
        private List<Object> proficiencyChoices; // ευέλικτο - Choice schema
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prerequisite {
        private ApiReference abilityScore;
        private int minimumScore;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StartingEquipment {
        private ApiReference equipment;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Spellcasting {
        private int level;
        private ApiReference spellcastingAbility;
        private List<SpellcastingInfo> info;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpellcastingInfo {
        private String name;
        private List<String> desc;
    }
}