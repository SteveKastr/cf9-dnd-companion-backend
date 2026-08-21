package gr.aueb.cf9.dndcompanion.dto;

import gr.aueb.cf9.dndcompanion.model.roles.User;

/**
 * Converts User entities into their safe, public-facing DTO representation,
 * shared between AccountService (self) and UserService (admin listing).
 */
public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isActive()
        );
    }
}