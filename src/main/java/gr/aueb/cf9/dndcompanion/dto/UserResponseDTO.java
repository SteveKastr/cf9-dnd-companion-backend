package gr.aueb.cf9.dndcompanion.dto;

import gr.aueb.cf9.dndcompanion.model.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
    private boolean active;
}