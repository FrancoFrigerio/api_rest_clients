package ar.com.frigeriofranco.practic.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;

@Slf4j
@Service
public class UtilJWT {
    private final String SECRET_KEY="secret";

    public String secret_token(String authorizationHeader){
        return authorizationHeader.substring(7);
    }



    public Algorithm getAlgorithm(){
        return Algorithm.HMAC256(SECRET_KEY.getBytes());
    }

    JWTVerifier verifier(){
       return JWT.require(getAlgorithm()).build();
    }



    public DecodedJWT verifyInfo(String authorizationHeader){
            String token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            return decodedJWT;
    }

    public Collection<SimpleGrantedAuthority> getAuthorities(String [] roles){
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return authorities;
    }
}
