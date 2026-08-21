package gr.aueb.cf9.dndcompanion.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a monetary cost (e.g. equipment price, starting gold),
 * matching the SRD's {quantity, unit} pattern (e.g. 15 gp).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private int quantity;
    private String unit;
}