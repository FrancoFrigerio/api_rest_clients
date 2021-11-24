package ar.com.frigeriofranco.practic.dto;


import ar.com.frigeriofranco.practic.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {


    private Long id;

    private String name;

    private String surname;

    private String email;

    private String dress;

    private String doc;

    private String phone;

    private Double saldo;

    private List<BillResponseDto> bills;
}
