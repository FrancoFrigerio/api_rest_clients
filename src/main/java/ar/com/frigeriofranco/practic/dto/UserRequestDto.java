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

    @NotEmpty
    @Size(min = 2, max = 10, message = "The name field must be between 2 and 10 characters long")
    private String name;

    @NotEmpty(message = "The field surname cannot be empty")
    private String surname;


    @NotEmpty
    @Email(message = "The username must be an email account ")
    private String username;

    @Size(min = 8, max = 20 ,message = "password size must be from 8 to 20 characters")
    @NotEmpty(message = "The field password cannot be empty")
    private String password;
    /*@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
            message = "The password must have: " +
                        "*At least one lower case, *At least one upper case, *At least one number")*/

    @NotEmpty(message = "The roles can´t be empty")
    private Set<String> roles = new HashSet<>();
}
