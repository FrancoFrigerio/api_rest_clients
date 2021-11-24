package ar.com.frigeriofranco.practic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bills")
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date created_At;

    @NotNull
    private Double total;


    @NotNull
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "bills")
    private Client cliente;
}
