package gabryel.dev.login.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginUserRequest(@NotEmpty(message = "You need to enter you Email") String email,
                               @NotEmpty(message = "You need to enter you Password") String password) {
}
