package gr.aueb.cf9.dndcompanion.controller;

import gr.aueb.cf9.dndcompanion.dto.UpdateAccountRequestDTO;
import gr.aueb.cf9.dndcompanion.dto.UserResponseDTO;
import gr.aueb.cf9.dndcompanion.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints for authenticated users to view/update/delete their own
 * account. No role restriction — every authenticated user (Admin,
 * GameMaster, Player) can manage their own profile here.
 */

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public UserResponseDTO getMyAccount(Authentication authentication) {
        return accountService.getMyAccount(authentication);
    }

    @PutMapping
    public UserResponseDTO updateMyAccount(
            @Valid @RequestBody UpdateAccountRequestDTO request, Authentication authentication) {
        return accountService.updateMyAccount(request, authentication);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMyAccount(Authentication authentication) {
        accountService.deleteMyAccount(authentication);
        return ResponseEntity.noContent().build();
    }
}