package gr.aueb.cf9.dndcompanion.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiReference {
    private String index;
    private String name;
    private String url;
    private String note;
}