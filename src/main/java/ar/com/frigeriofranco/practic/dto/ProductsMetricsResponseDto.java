package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductsMetricsResponseDto {

    private Long idProduct;

    private String nameProduct;

    private Integer countProduct;

}
