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
 * Represents a playable race (e.g. Elf, Dwarf). Related entities —
 * Subrace and Trait — live in their own collections and are referenced
 * here as lightweight ApiReference links, matching the SRD
 * API structure.
 */

@Document(collection = "races")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    @Id
    private String id;

    private String index;
    private String name;
    private int speed;
    private List<AbilityBonus> abilityBonuses;
    private Object abilityBonusOptions;
    private String alignment;
    private String age;
    private String size;
    private String sizeDescription;
    private List<ApiReference> startingProficiencies;
    private Object startingProficiencyOptions;
    private List<ApiReference> languages;
    private String languageDesc;
    private Object languageOptions;
    private List<ApiReference> traits;
    private List<ApiReference> subraces;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbilityBonus {
        private ApiReference abilityScore;
        private int bonus;
    }
}