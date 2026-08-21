package gr.aueb.cf9.dndcompanion.dto;

import gr.aueb.cf9.dndcompanion.model.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response returned after successful login/register, containing the
 * JWT access token and basic user info for the frontend AuthContext.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String username;
    private Role role;
}