package com.strangely.backend.Config.AuthConfig;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Service.Exception.Restexception;
import com.strangely.backend.Service.Auth.Implementation.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;




@RequiredArgsConstructor
@Component
public class AuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String key;

    private final UserService userService;

    private final int seconds = 21600000;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String createToken(UserDTO user) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + seconds);

        Algorithm algorithm = Algorithm.HMAC256(key);
        String subject = user.getUserName();
        String firstName= user.getFirstName();
        String lastName =  user.getLastName();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("FirstName", firstName)
                .withClaim("LastName", lastName)
                .sign(algorithm);

    }

    public Authentication validateJWT(String password) {
        Algorithm algorithm = Algorithm.HMAC256(key);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(password);
        String firstName = decoded.getClaim("FirstName").asString();
        String lastName = decoded.getClaim("LastName").asString();

        UserDTO user = UserDTO.builder()
                .userName(decoded.getSubject())
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public UserDTO getUserByJWT(String pass) {

        Algorithm algo = Algorithm.HMAC256(key);

        JWTVerifier verifier = JWT.require(algo)
                .build();

        DecodedJWT d = verifier.verify(pass);

        UserDTO u = userService.findByUsername(d.getSubject());

        if(u == null)
        {
            throw new Restexception(" User Not Found", HttpStatus.BAD_REQUEST);
        }
        return u;
    }
    public Authentication validateJWTForce(String token) {
        UserDTO u = getUserByJWT(token);
        return new UsernamePasswordAuthenticationToken(u, null, Collections.emptyList());
    }


}
