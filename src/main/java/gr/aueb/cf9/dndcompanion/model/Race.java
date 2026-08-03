package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private Object abilityBonusOptions; // ευέλικτο
    private String alignment;
    private String age;
    private String size;
    private String sizeDescription;
    private List<ApiReference> startingProficiencies;
    private Object startingProficiencyOptions; // ευέλικτο
    private List<ApiReference> languages;
    private String languageDesc;
    private Object languageOptions; // ευέλικτο
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