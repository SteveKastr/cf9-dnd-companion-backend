package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.dto.UserResponseDTO;
import gr.aueb.cf9.dndcompanion.exceptions.EntityNotFoundException;
import gr.aueb.cf9.dndcompanion.model.roles.User;
import gr.aueb.cf9.dndcompanion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import gr.aueb.cf9.dndcompanion.dto.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public void deleteUser(String id, Authentication authentication) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

        if (userToDelete.getUsername().equals(authentication.getName())) {
            throw new IllegalArgumentException("You cannot delete your own account");
        }

        userRepository.deleteById(id);
    }

}