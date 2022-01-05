package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientMetricsDto {

    private Long id;

    private String name;

    private String surname;

    private Double total;
}
