package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.dto.AuthResponseDTO;
import gr.aueb.cf9.dndcompanion.dto.LoginRequestDTO;
import gr.aueb.cf9.dndcompanion.dto.RegisterRequestDTO;
import gr.aueb.cf9.dndcompanion.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}