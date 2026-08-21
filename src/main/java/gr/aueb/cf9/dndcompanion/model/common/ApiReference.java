package gr.aueb.cf9.dndcompanion.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lightweight reference to another SRD entity (e.g. a Race, Spell, or Item),
 * matching the {index, name, url} pattern used throughout 5e SRD data.
 * Embedded inside other entities rather than stored separately.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiReference {
    private String index;
    private String name;
    private String url;
    private String note;
}