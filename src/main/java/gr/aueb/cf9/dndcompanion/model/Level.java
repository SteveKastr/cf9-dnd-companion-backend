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
 * Represents a single character level (1-20) for a class, including
 * which features are gained and (if applicable) spellcasting progression.
 *
 * NOTE: LevelSpellcasting fields use explicit @JsonProperty annotations
 * because the SNAKE_CASE naming strategy doesn't insert an underscore
 * before trailing digits (e.g. "spellSlotsLevel1" would incorrectly
 * become "spell_slots_level1" instead of the actual JSON key
 * "spell_slots_level_1").
 */

@Document(collection = "levels")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    private String id;

    private String index;
    private int level;
    private Integer abilityScoreBonuses;
    private Integer profBonus;
    private List<ApiReference> features;
    @JsonProperty("class")
    private ApiReference characterClass;
    private Object classSpecific;
    private LevelSpellcasting spellcasting;
    private ApiReference subclass;
    private Object subclassSpecific;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LevelSpellcasting {
        private Integer cantripsKnown;

        @JsonProperty("spell_slots_level_1")
        private int spellSlotsLevel1;

        @JsonProperty("spell_slots_level_2")
        private int spellSlotsLevel2;

        @JsonProperty("spell_slots_level_3")
        private int spellSlotsLevel3;

        @JsonProperty("spell_slots_level_4")
        private int spellSlotsLevel4;

        @JsonProperty("spell_slots_level_5")
        private int spellSlotsLevel5;

        @JsonProperty("spell_slots_level_6")
        private Integer spellSlotsLevel6;

        @JsonProperty("spell_slots_level_7")
        private Integer spellSlotsLevel7;

        @JsonProperty("spell_slots_level_8")
        private Integer spellSlotsLevel8;

        @JsonProperty("spell_slots_level_9")
        private Integer spellSlotsLevel9;

        private Integer spellsKnown;
    }
}