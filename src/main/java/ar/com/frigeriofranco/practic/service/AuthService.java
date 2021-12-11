package ar.com.frigeriofranco.practic.service;

import ar.com.frigeriofranco.practic.dto.AuthenticationRequestDto;
import ar.com.frigeriofranco.practic.dto.AuthenticationResponseDto;

public interface AuthService {

    public AuthenticationResponseDto createAuthentication(AuthenticationRequestDto authenticationRequest) throws Exception;
}
