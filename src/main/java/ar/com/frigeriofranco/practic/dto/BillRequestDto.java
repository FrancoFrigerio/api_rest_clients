package ar.com.frigeriofranco.practic.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto {

    @NotNull(message = "You must indicate the date")
    private Date created_At;

    @NotNull(message = "The total is required")
    private Double total;

    @NotBlank(message = "You most to provide the description")
    private String description;

    @NotEmpty
    private List<ItemRequestDto> itemsProducts;

}
