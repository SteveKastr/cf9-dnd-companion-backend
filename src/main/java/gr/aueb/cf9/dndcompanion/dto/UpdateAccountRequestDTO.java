package gr.aueb.cf9.dndcompanion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Payload for a user updating their own account. Deliberately excludes
 * username and password. Username stays fixed after registration, and
 * password changes are out of scope for this project.
 */

@Data
public class UpdateAccountRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
}