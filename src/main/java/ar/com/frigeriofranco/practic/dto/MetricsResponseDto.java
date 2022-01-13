package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MetricsResponseDto {

    private Double total;

    private Integer count;

    private Integer month;

    private Integer year;
}
