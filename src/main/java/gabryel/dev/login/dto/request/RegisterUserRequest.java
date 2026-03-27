package gabryel.dev.login.dto.request;


import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(@NotEmpty(message = "You need to enter your name") String name,
                                  @NotEmpty(message = "You need to enter you Email") String email,
                                  @NotEmpty(message = "You need to enter you Email") String password) {
}
