package ar.com.frigeriofranco.practic.controller;


import ar.com.frigeriofranco.practic.dto.AuthenticationRequestDto;
import ar.com.frigeriofranco.practic.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
@Slf4j
public class AuthCotroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto auth)throws Exception{
        return new ResponseEntity<>(authService.createAuthentication(auth), HttpStatus.ACCEPTED);
    }
}
