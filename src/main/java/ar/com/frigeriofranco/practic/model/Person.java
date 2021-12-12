package ar.com.frigeriofranco.practic.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The surname cannot be empty")
    private String surname;

    @NotBlank(message = "The email cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "The dress cannot be empty")
    private String dress;

    @NotBlank(message = "The docuement cannot be empty")
    private String doc;

    @NotBlank(message = "The name cannot be empty")
    private String phone;

    @DateTimeFormat(pattern = "dd/MM/yyy")
    private Date date_birth;

    //@BooleanFlag
    private boolean deleted = Boolean.FALSE;


}
