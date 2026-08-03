package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "spells")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spell {

    @Id
    private String id;

    private String index;
    private String name;
    private List<String> desc;
    private List<String> higherLevel;
    private String range;
    private List<String> components;
    private String material;
    private boolean ritual;
    private String duration;
    private boolean concentration;
    private String castingTime;
    private int level;
    private String attackType;
    private SpellDamage damage;
    private SpellDC dc;
    private AreaOfEffect areaOfEffect;
    private Map<String, String> healAtSlotLevel;
    private ApiReference school;
    private List<ApiReference> classes;
    private List<ApiReference> subclasses;
    private String url;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpellDamage {
        private ApiReference damageType;
        private Map<String, String> damageAtSlotLevel;
        private Map<String, String> damageAtCharacterLevel;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpellDC {
        private ApiReference dcType;
        private String dcSuccess;
        private String desc;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AreaOfEffect {
        private int size;
        private String type;
    }
}