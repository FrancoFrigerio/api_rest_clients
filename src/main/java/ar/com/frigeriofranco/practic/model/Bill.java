package ar.com.frigeriofranco.practic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotBlank(message = "You must indicate the date")
    private Date created_At;

    @NotNull(message = "The total is required")
    private Double total;

    @NotBlank(message = "You most to provide the description")
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "bills")
    private Client cliente;

}
