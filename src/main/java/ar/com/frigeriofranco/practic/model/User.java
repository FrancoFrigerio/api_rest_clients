package ar.com.frigeriofranco.practic.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.FetchType.*  ;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Size(min = 2, max = 20, message = "The name field must be between 2 and 10 characters long")
    @NotEmpty
    private String name;

    @NotEmpty(message = "The field surname cannot be empty")
    private String surname;

    @NotEmpty
    @NotBlank
    @Email(message = "The username must be an email account ")
    private String username;

    //@Size(min = 8, max = 20, message = "The name field must be between 8 and 20 characters long")
    @NotEmpty(message = "The field password cannot be empty")
    private String password;

    @NotEmpty
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();

}
