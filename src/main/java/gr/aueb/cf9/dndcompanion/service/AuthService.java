package gr.aueb.cf9.dndcompanion.service;

import gr.aueb.cf9.dndcompanion.dto.AuthResponseDTO;
import gr.aueb.cf9.dndcompanion.dto.LoginRequestDTO;
import gr.aueb.cf9.dndcompanion.dto.RegisterRequestDTO;
import gr.aueb.cf9.dndcompanion.exceptions.EmailAlreadyExistsException;
import gr.aueb.cf9.dndcompanion.exceptions.InvalidRoleException;
import gr.aueb.cf9.dndcompanion.exceptions.UserNotFoundException;
import gr.aueb.cf9.dndcompanion.exceptions.UsernameAlreadyExistsException;
import gr.aueb.cf9.dndcompanion.model.Role;
import gr.aueb.cf9.dndcompanion.model.User;
import gr.aueb.cf9.dndcompanion.repository.UserRepository;
import gr.aueb.cf9.dndcompanion.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {

        if (request.getRole() == Role.ADMIN) {
            throw new InvalidRoleException("Cannot self-register as Admin");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(true);

        userRepository.save(user);

        String token = jwtService.generateToken(toUserDetails(user));

        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token = jwtService.generateToken(toUserDetails(user));

        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().name())
                .build();
    }
}