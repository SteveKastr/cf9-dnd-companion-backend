package gr.aueb.cf9.dndcompanion.dto;

import gr.aueb.cf9.dndcompanion.model.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String username;
    private Role role;
}