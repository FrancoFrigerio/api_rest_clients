package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Set;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {

    private String username;
    private String token;
    private Set<String> roles;
}
