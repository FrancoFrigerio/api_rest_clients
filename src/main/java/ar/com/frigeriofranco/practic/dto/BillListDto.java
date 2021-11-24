package ar.com.frigeriofranco.practic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillListDto {

    private Long id;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date created_At;

    private Double total;

    private String description;

    private ClientListRespDto  client;
}
