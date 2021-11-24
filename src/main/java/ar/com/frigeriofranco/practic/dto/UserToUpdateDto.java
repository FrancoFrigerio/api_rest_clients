package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToUpdateDto {


    @NotEmpty
    @Size(min = 2, max = 10, message = "The name field must be between 2 and 10 characters long")
    private String name;

    @NotEmpty(message = "The field surname cannot be empty")
    private String surname;

    @NotEmpty
    @NotBlank
    @Email(message = "The username must be an email account ")
    private String username;

    @NotEmpty(message = "The field password cannot be empty")
    private String password;
}
