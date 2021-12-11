package ar.com.frigeriofranco.practic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {


    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date created_At;


    private String urlPhoto1;


    private String urlPhoto2;

    @PrePersist
    public void prePersist(){
        created_At = new Date();
    }
}
