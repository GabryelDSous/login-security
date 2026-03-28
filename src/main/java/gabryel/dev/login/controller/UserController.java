package gabryel.dev.login.controller;

import gabryel.dev.login.dto.request.LoginUserRequest;
import gabryel.dev.login.dto.request.RegisterUserRequest;
import gabryel.dev.login.dto.response.LoginUserResponse;
import gabryel.dev.login.dto.response.RegisterUserResponse;
import gabryel.dev.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequest));
    }
    @PostMapping("/register-admin")
    public ResponseEntity<RegisterUserResponse> registerAdmin(@RequestBody @Valid RegisterUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerAdmin(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Valid LoginUserRequest userRequest) {
        return ResponseEntity.ok(userService.loginUser(userRequest));
    }
}
