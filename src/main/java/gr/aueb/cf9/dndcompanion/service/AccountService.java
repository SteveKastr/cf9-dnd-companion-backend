package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.dto.UpdateAccountRequestDTO;
import gr.aueb.cf9.dndcompanion.dto.UserResponseDTO;
import gr.aueb.cf9.dndcompanion.exceptions.EmailAlreadyExistsException;
import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.roles.Role;
import gr.aueb.cf9.dndcompanion.model.roles.User;
import gr.aueb.cf9.dndcompanion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;

    public UserResponseDTO getMyAccount(Authentication authentication) {
        User user = getCurrentUser(authentication);
        return toDto(user);
    }

    public UserResponseDTO updateMyAccount(UpdateAccountRequestDTO request, Authentication authentication) {
        User user = getCurrentUser(authentication);

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);
        return toDto(user);
    }

    public void deleteMyAccount(Authentication authentication) {
        User user = getCurrentUser(authentication);

        if (user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException(
                    "Admins cannot delete their own account.");
        }

        userRepository.deleteById(user.getId());
    }

    private User getCurrentUser(Authentication authentication) {
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private UserResponseDTO toDto(User user) {
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