package gabryel.dev.login.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import gabryel.dev.login.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    @Value(value = "${JWT_SECRET}")
    private String secrect;

    public String generateToken(UserModel userModel) {
        Algorithm algorithm = Algorithm.HMAC256(secrect);

        return JWT.create()
                .withClaim("user_id", userModel.getId().toString())
                .withSubject(userModel.getEmail())
                .withClaim("role", userModel.getRole().toString())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(algorithm);
    }

    public Optional<JWTUserDate> validationToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secrect);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return Optional.of(
                    JWTUserDate.builder()
                            .user_id(decode.getClaim("user_id").asString())
                            .email(decode.getSubject())
                            .role(decode.getClaim("role").asString())
                            .build()
            );
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
