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
 * Represents a racial trait (e.g. Darkvision, Dwarven Resilience).
 * Shared between Race and Subrace via reference — the same trait can
 * apply to multiple races/subraces.
 */

@Document(collection = "traits")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trait {

    @Id
    private String id;

    private String index;
    private String name;
    private List<String> desc;
    private List<ApiReference> races;
    private List<ApiReference> subraces;
    private List<ApiReference> proficiencies;
    private Object proficiencyChoices;
    private Object languageOptions;
    private ApiReference parent;
    private Object traitSpecific;
    private String url;
}
