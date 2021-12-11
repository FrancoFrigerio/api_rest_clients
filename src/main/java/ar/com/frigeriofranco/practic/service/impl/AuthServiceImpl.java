package ar.com.frigeriofranco.practic.service.impl;


import ar.com.frigeriofranco.practic.dto.AuthenticationRequestDto;
import ar.com.frigeriofranco.practic.dto.AuthenticationResponseDto;
import ar.com.frigeriofranco.practic.exceptions.BadCredentialsExceptions;
import ar.com.frigeriofranco.practic.repository.UserRepository;
import ar.com.frigeriofranco.practic.service.AuthService;
import ar.com.frigeriofranco.practic.util.UtilJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilJWT utilJWT;

    @Autowired
    private UserRepository userRepository;

    @Value("error.email.not.found")
    private String emailAcountNotFound;

    @Value("error.password.incorrect")
    private String passwordIncorrect;

    @Value("error.bad-credentials")
    private String badCredentials;

    @Autowired
    private MessageSource messageSource;


    public AuthenticationResponseDto createAuthentication(AuthenticationRequestDto authenticationRequest) throws Exception {
        User user = null;
        try{
             user = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())).getPrincipal();
        }catch (Exception e){
            log.info(e.getMessage());
            if(e.getMessage() == null){
                throw new BadCredentialsExceptions(messageSource.getMessage(emailAcountNotFound,null, Locale.getDefault()));
            }else if (e.getMessage().equals("Bad credentials")){
                throw new BadCredentialsExceptions(messageSource.getMessage(passwordIncorrect,null,Locale.getDefault()));
            }
        }
        String jwt = utilJWT.generateToken(user);
        Set<String> nameRoles = new HashSet<>();
            user.getAuthorities().forEach(auth->{
                nameRoles.add(auth.getAuthority());
            });
        return new AuthenticationResponseDto(user.getUsername(),jwt,nameRoles);
    }
}
