package ar.com.frigeriofranco.practic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDto {


    @NotNull
    private int sizePage;

    @NotNull
    private int page;

    @NotBlank(message = "You must indicate the field to order")
    private String sort;


    private String order;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date desde;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date hasta;

}
