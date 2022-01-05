package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {

    @NotNull
    private Long productID;

    @NotNull
    private Integer productCount;
}
