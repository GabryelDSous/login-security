package gabryel.dev.login.mapper;

import gabryel.dev.login.dto.request.RegisterUserRequest;
import gabryel.dev.login.dto.response.RegisterUserResponse;
import gabryel.dev.login.model.UserModel;

public class UserMapper {

    public static UserModel toModel(RegisterUserRequest userRequest) {
        UserModel userModel = new UserModel();
        userModel.setName(userRequest.name());
        userModel.setEmail(userRequest.email());
        return userModel;
    }

    public static RegisterUserResponse toDTO(UserModel userModel) {
        return new RegisterUserResponse(
                userModel.getName(), userModel.getEmail()
        );
    }
}
