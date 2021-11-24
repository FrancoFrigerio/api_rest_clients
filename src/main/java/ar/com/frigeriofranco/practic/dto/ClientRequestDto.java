package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String dress;

    @NotNull
    private String doc;

    @NotNull
    private String phone;

    @DateTimeFormat(pattern = "dd/MM/yyy")
    private Date date_birth;
}
