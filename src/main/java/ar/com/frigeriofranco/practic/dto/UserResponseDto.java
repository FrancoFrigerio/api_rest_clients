package ar.com.frigeriofranco.practic.dto;


import ar.com.frigeriofranco.practic.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String name;

    private String surname;

    private List<Role> roles;


}
