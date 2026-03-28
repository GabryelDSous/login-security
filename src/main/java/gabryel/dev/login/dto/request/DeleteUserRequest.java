package gabryel.dev.login.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserRequest(@NotBlank(message = "You need to enter you email") String email,
                                @NotBlank(message = "You need to enter you password") String password) {
}
