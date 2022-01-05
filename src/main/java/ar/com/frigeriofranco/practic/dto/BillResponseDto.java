package ar.com.frigeriofranco.practic.dto;


import ar.com.frigeriofranco.practic.model.Item;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDto {


    private Long id;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date created_At;

    private Double total;

    private String description;

}
