package gr.aueb.cf9.dndcompanion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Login. Deliberately returns a generic "Invalid username
 * or password" error on failure (handled by Spring Security /
 * ErrorHandler) to avoid revealing which usernames exist.
 */

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}