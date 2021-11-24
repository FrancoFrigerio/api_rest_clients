package ar.com.frigeriofranco.practic.security;

import ar.com.frigeriofranco.practic.util.UtilJWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    private static  final UtilJWT utilJWT = new UtilJWT();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
                if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                    try{
                        DecodedJWT decodedJWT = utilJWT.verifyInfo(authorizationHeader);
                        String [] roles = decodedJWT.getClaim("roles").asArray(String.class);

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(),null, utilJWT.getAuthorities(roles));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                        filterChain.doFilter(request,response);

                    }catch (Exception e){
                           log.error("Error login ing : {}"+ e.getMessage());
                        response.setStatus(SC_FORBIDDEN);
                        Map<String,String> error = new HashMap<>();
                        error.put("error_message",e.getMessage());
                        response.setContentType(APPLICATION_JSON_VALUE);
                        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
                        }

                }else{
                    filterChain.doFilter(request,response);
                }
        }
    }
}
