package gabryel.dev.login.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateNameEmailUserRequest(@NotEmpty(message = "You need to enter you Email") String password,
                                         @NotEmpty(message = "You need to enter your name") String name,
                                         @NotEmpty(message = "You need to enter you Email") String email) {
}