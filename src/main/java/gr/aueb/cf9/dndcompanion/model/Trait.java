package gr.aueb.cf9.dndcompanion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.aueb.cf9.dndcompanion.model.common.ApiReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private Object proficiencyChoices;   // ευέλικτο - Choice schema
    private Object languageOptions;      // ευέλικτο - Choice schema
    private ApiReference parent;
    private Object traitSpecific;        // ευέλικτο - breath weapon, spell options, κ.λπ.
    private String url;
}
