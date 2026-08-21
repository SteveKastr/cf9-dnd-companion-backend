package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import gr.aueb.cf9.dndcompanion.model.common.Cost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents a piece of equipment — weapon, armor, tool, gear, vehicle,
 * or magic item — all unified into a single collection with an
 * itemType flag ("mundane"/"magic") distinguishing the two categories.
 * Fields not relevant to a given item's category are simply null.
 */

@Document(collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private String id;

    private String index;
    private String name;
    private ApiReference equipmentCategory;
    private Cost cost;
    private List<String> desc;
    private Double weight;
    private String image;

    // Πεδία που εξαρτώνται από την κατηγορία (weapon/armor/tool/vehicle/gear)
    private ApiReference gearCategory;
    private String armorCategory;
    private ArmorClass armorClass;
    private String capacity;
    private String categoryRange;
    private List<Content> contents;
    private Damage damage;
    private List<ApiReference> properties;
    private Integer quantity;
    private RangeInfo range;
    private List<String> special;
    private Boolean stealthDisadvantage;
    private Integer strMinimum;
    private ThrowRange throwRange;
    private String toolCategory;
    private Damage twoHandedDamage;
    private String vehicleCategory;
    private String weaponCategory;
    private String weaponRange;
    private SpeedInfo speed;

    // Πεδίο, ξεχωρίζει equipment από magic items
    private String itemType; // "mundane" ή "magic"

    // Πεδία μόνο για magic items
    private Rarity rarity;
    private List<ApiReference> variants;
    private Boolean variant;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ArmorClass {
        private int base;
        private boolean dexBonus;
        private Integer maxBonus;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Damage {
        private ApiReference damageType;
        private String damageDice;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RangeInfo {
        private int normal;
        @JsonProperty("long")
        private Integer longRange;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ThrowRange {
        private int normal;
        @JsonProperty("long")
        private int longRange;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpeedInfo {
        private double quantity;
        private String unit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private ApiReference item;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rarity {
        private String name;
    }
}