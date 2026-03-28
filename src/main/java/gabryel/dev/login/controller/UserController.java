package gabryel.dev.login.controller;

import gabryel.dev.login.dto.request.*;
import gabryel.dev.login.dto.response.ListUserResponse;
import gabryel.dev.login.dto.response.LoginUserResponse;
import gabryel.dev.login.dto.response.RegisterUserResponse;
import gabryel.dev.login.dto.response.UpdateNameEmailUserResponse;
import gabryel.dev.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-admin")
    public ResponseEntity<RegisterUserResponse> registerAdmin(@RequestBody @Valid RegisterUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerAdmin(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Valid LoginUserRequest userRequest) {
        return ResponseEntity.ok(userService.loginUser(userRequest));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody @Valid DeleteUserRequest userRequest) {
        userService.deleteUser(userRequest);
        return ResponseEntity.ok("Successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateNameEmailUserResponse> updateNameEmailUser(@PathVariable(name = "id") UUID id, @RequestBody @Valid UpdateNameEmailUserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ListUserResponse>> listAll() {
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody @Valid UpdatePasswordUserRequest userRequest) {
        userService.updatePassword(userRequest);
        return ResponseEntity.ok("Password successfully updated");
    }
}
