package ar.com.frigeriofranco.practic.security;

import ar.com.frigeriofranco.practic.util.UtilJWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.*;


import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private static  final UtilJWT utilJWT = new UtilJWT();


    @Value("error.token.invalid")
    private String invalidToken;

    @Value("error.token.malformed")
    private String malformedToken;

    @Value("error.token.expired")
    private String expiredToken;

    @Value("error.token.is-not-present")
    private String tokenIsNotPresent;

    @Autowired
    MessageSource messageSource;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String,String> error = new HashMap<>();
        if(request.getServletPath().equals("/auth/login")){
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
                if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                    try {
                        DecodedJWT decodedJWT = utilJWT.verifyInfo(authorizationHeader);
                        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, utilJWT.getAuthorities(roles));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);
                    }catch (Exception e){
                        error.put("error_message",e.getMessage());
                        response.setStatus(SC_FORBIDDEN);
                        response.setContentType(APPLICATION_JSON_VALUE);
                        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
                    }
                }else{
                    response.setStatus(SC_FORBIDDEN);
                    error.put("error_message","sin token");
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.getWriter().write(new ObjectMapper().writeValueAsString(error));

                }

        }

    }


}
