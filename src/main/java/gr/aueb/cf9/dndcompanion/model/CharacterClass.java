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
 * Represents a D&D character class (e.g. Wizard, Fighter).
 * ClassLevels and spells are URL strings, not embedded data — the actual
 * level-by-level breakdown lives in separate Level/Feature collections,
 * fetched via LevelController by className.
 */

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
    private List<Object> proficiencyChoices;
    private List<ApiReference> savingThrows;
    private List<StartingEquipment> startingEquipment;
    private List<Object> startingEquipmentOptions;
    private List<ApiReference> subclasses;
    private Spellcasting spellcasting;
    private String spells; // URL string
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultiClassing {
        private List<Prerequisite> prerequisites;
        private Object prerequisiteOptions;
        private List<ApiReference> proficiencies;
        private List<Object> proficiencyChoices;
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