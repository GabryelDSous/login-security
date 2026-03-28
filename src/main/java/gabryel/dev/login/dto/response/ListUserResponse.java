package gabryel.dev.login.dto.response;

import gabryel.dev.login.saveenum.Roles;

import java.util.UUID;

public record ListUserResponse(UUID id, String name, String email, Roles role) {
}
