package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientListRespDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String dress;

}
