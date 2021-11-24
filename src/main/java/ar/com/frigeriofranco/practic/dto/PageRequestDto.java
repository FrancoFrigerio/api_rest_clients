package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageRequestDto {


    @NotNull
    private int sizePage;

    @NotNull
    private int page;

    @NotNull
    private String sort;

    @NotNull
    private String order;
}
