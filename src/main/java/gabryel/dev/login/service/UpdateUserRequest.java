package gabryel.dev.login.service;

import jakarta.validation.constraints.NotEmpty;

public record UpdateUserRequest(@NotEmpty(message = "You need to enter your name") String name,
                                @NotEmpty(message = "You need to enter you Email") String email,
                                @NotEmpty(message = "You need to enter you Email") String password) {
}
