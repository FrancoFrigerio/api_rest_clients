package ar.com.frigeriofranco.practic.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto {

    @NotNull
    private Date created_At;

    @NotNull
    private Double total;

    @NotNull
    private String description;

}
