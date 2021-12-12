package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The surname cannot be empty")
    private String surname;

    @NotBlank(message = "The email cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "The dress cannot be empty")
    private String dress;

    @NotBlank(message = "The document cannot be empty")
    private String doc;

    @NotBlank(message = "The phone cannot be empty")
    private String phone;

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date_birth;
}
