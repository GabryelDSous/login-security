package gabryel.dev.login.service;

import gabryel.dev.login.config.TokenConfig;
import gabryel.dev.login.dto.request.LoginUserRequest;
import gabryel.dev.login.dto.request.RegisterUserRequest;
import gabryel.dev.login.dto.response.LoginUserResponse;
import gabryel.dev.login.dto.response.RegisterUserResponse;
import gabryel.dev.login.mapper.UserMapper;
import gabryel.dev.login.model.UserModel;
import gabryel.dev.login.repository.UserRepository;
import gabryel.dev.login.saveenum.Roles;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenConfig tokenConfig, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
    }

    public RegisterUserResponse registerUser(RegisterUserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.email()).isPresent())
            throw new UsernameNotFoundException("User not found");
        UserModel userModel = UserMapper.toModel(userRequest);
        userModel.setPassword(passwordEncoder.encode(userRequest.password()));
        userModel.setRole(Roles.USER);
        userRepository.save(userModel);
        return UserMapper.toDTO(userModel);
    }

    public RegisterUserResponse registerAdmin(RegisterUserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.email()).isPresent())
            throw new UsernameNotFoundException("User not found");
        UserModel userModel = UserMapper.toModel(userRequest);
        userModel.setPassword(passwordEncoder.encode(userRequest.password()));
        userModel.setRole(Roles.ADMIN);
        userRepository.save(userModel);
        return UserMapper.toDTO(userModel);
    }

    public LoginUserResponse loginUser(LoginUserRequest userRequest) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        UserModel userModel = (UserModel) authentication.getPrincipal();
        String token = tokenConfig.generateToken(userModel);
        return new LoginUserResponse(token);
    }
}
