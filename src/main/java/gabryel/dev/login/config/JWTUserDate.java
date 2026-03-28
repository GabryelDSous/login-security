package gabryel.dev.login.config;

import lombok.Builder;

@Builder
public record JWTUserDate(String user_id, String email, String role) {
}
