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
 * Represents a subrace (e.g. Hill Dwarf, High Elf), with its own
 * ability bonuses and racial traits layered on top of the parent Race.
 */

@Document(collection = "subraces")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subrace {

    @Id
    private String id;

    private String index;
    private String name;
    private ApiReference race;
    private String desc;
    private List<Race.AbilityBonus> abilityBonuses;
    private List<ApiReference> racialTraits;
    private String url;
}
