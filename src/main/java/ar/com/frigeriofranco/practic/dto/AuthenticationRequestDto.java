package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {

    @NotBlank(message = "The username is required")
    @Email
    private String username;
    @NotNull
    @Size(min = 2, max = 20, message = "The name field must be between 2 and 10 characters long")
    private String password;
}
