package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "monsters")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    @Id
    private String id;

    private String index;
    private String name;
    private String desc;
    private String size;
    private String type;
    private String subtype;
    private String alignment;

    private List<ArmorClassEntry> armorClass;
    private int hitPoints;
    private String hitDice;
    private String hitPointsRoll;
    private Speed speed;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private List<ProficiencyEntry> proficiencies;
    private List<String> damageVulnerabilities;
    private List<String> damageResistances;
    private List<String> damageImmunities;
    private List<ApiReference> conditionImmunities;
    private Senses senses;
    private String languages;

    private double challengeRating;
    private Integer proficiencyBonus;
    private int xp;

    // Μεταβλητή δομή
    private List<Object> specialAbilities;
    private List<Object> actions;
    private List<Object> legendaryActions;
    private List<Object> reactions;

    private List<ApiReference> forms;
    private String image;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArmorClassEntry {
        private String type;
        private int value;
        private List<ApiReference> armor;
        private ApiReference condition;
        private ApiReference spell;
        private String desc;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Speed {
        private String walk;
        private String burrow;
        private String climb;
        private String fly;
        private String swim;
        private Boolean hover;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Senses {
        private int passivePerception;
        private String blindsight;
        private String darkvision;
        private String tremorsense;
        private String truesight;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProficiencyEntry {
        private int value;
        private ApiReference proficiency;
    }
}