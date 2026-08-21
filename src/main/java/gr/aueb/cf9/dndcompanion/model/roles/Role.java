package gr.aueb.cf9.dndcompanion.model.roles;

/**
 * The three application roles. Note: ADMIN cannot be selected during
 * public self-registration (enforced in AuthService) — the only admin
 * account is created via AdminUserSeeder on first application startup.
 */

public enum Role {
    ADMIN,
    GAME_MASTER,
    PLAYER
}
