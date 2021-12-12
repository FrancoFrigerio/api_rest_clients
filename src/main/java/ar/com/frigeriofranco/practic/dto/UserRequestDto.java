package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Size(min = 2, max = 20, message = "The name field must be between 2 and 20 characters long")
    @NotEmpty
    private String name;

    @NotEmpty(message = "The field surname cannot be empty")
    private String surname;


    @NotEmpty
    @Email(message = "The username must be an email account ")
    private String username;

    @Size(min = 8, max = 20 ,message = "password size must be from 8 to 20 characters")
    @NotEmpty(message = "The field password cannot be empty")
    private String password;


    @NotEmpty(message = "The roles can´t be empty")
    private Set<String> roles = new HashSet<>();
}
